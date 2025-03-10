import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

# ------- ACTIVIDAD 4 -------

# Cargar el CSV

df = pd.read_csv('all_seasons.csv')

# Seleccionar las columnas necesarias

df = df[['pts', 'ast']]

# Procesar datos faltantes y rellenar valores faltantes

df = df.dropna()

# Definir variables predictoras (X) y variable objetivo (y)

X = df[['pts']]
y = df[['ast']]

# Asegurar que X e y tengan el mismo tamaño después de eliminar NaN

X, y = X.align(y, join='inner', axis=0)

# Dividir en entrenamiento y test (80%-20%)

split_point = int(len(df) * 0.8)
X_train, X_test = X[:split_point], X[split_point:]
y_train, y_test = y[:split_point], y[split_point:]

# Crear el modelo y ajustarlo a los datos

model = LinearRegression()
model.fit(X_train, y_train)

# Predicciones

y_pred = model.predict(X_test)

# Evaluación del RMSE

mse = mean_squared_error(y_test, y_pred)
rmse = mse ** 0.5
print(f'Error RMSE: {rmse:.2f}')

# Coeficientes

print("Intercepto: ", model.intercept_[0])
print("Coeficientes:")
for feature, coef in zip(['ast'], model.coef_[0]):
    print(f"  {feature}: {coef:.4f}")

# SELECCIONAR SOLO 25 VALORES PARA LA GRÁFICA 
y_test_sample = y_test.iloc[:25]  # Tomar solo los primeros 25 valores
y_pred_sample = y_pred[:25]  # Tomar las 25 primeras predicciones
player_ids = y_test_sample.index  # Obtener los índices (IDs de jugadores)

# Graficar las predicciones vs valores reales para 25 jugadores
plt.figure(figsize=(12, 6))
plt.plot(player_ids, y_test_sample, color='blue', label='Valores Reales') 
plt.plot(player_ids, y_pred_sample, color='red', marker='x', label='Prediccion Lineal Simple')

# Gráfico de comparación

plt.xticks(rotation=45)  # Rotar etiquetas del eje X
plt.xlabel('ID del Jugador')
plt.ylabel('Media de Puntos')
plt.title('Predicción de Media de Puntos por Asistencia - 25 Jugadores')
plt.legend()
plt.grid(True)
plt.show()

# --- ELIMINACIÓN DE OUTLIERS ---

def eliminar_outliers(df, columna):
    Q1 = df[columna].quantile(0.25)
    Q3 = df[columna].quantile(0.75)
    IQR = Q3 - Q1
    filtro = (df[columna] >= (Q1 - 1.5 * IQR)) & (df[columna] <= (Q3 + 1.5 * IQR))
    return df[filtro]

# Aplicar la eliminación de outliers en ambas columnas
df_sin_outliers = eliminar_outliers(df, 'pts')
df_sin_outliers = eliminar_outliers(df_sin_outliers, 'ast')

# Redefinir variables después de quitar outliers
X = df_sin_outliers[['pts']]
y = df_sin_outliers[['ast']]

# Dividir en entrenamiento y test nuevamente (80%-20%)
split_point = int(len(df_sin_outliers) * 0.8)
X_train, X_test = X[:split_point], X[split_point:]
y_train, y_test = y[:split_point], y[split_point:]

# Crear y entrenar el modelo después de quitar outliers
model = LinearRegression()
model.fit(X_train, y_train)

# Predicciones después de eliminar outliers
y_pred = model.predict(X_test)

# Evaluación del RMSE después de quitar outliers
mse = mean_squared_error(y_test, y_pred)
rmse = mse ** 0.5
print(f'Error RMSE después de eliminar outliers: {rmse:.2f}')

# SELECCIONAR SOLO 25 VALORES PARA LA GRÁFICA SIN OUTLIERS
y_test_sample = y_test.iloc[:25]
y_pred_sample = y_pred[:25]
player_ids = y_test_sample.index

# Gráfica después de quitar outliers
plt.figure(figsize=(12, 6))
plt.plot(player_ids, y_test_sample, color='blue', label='Valores Reales (Sin Outliers)')
plt.plot(player_ids, y_pred_sample, color='red', marker='x', label='Predicción Lineal Simple (Sin Outliers)')
plt.xlabel('ID del Jugador')
plt.ylabel('Media de Puntos')
plt.title('Predicción de Media de Puntos por Asistencia - Después de quitar outliers')
plt.legend()
plt.grid(True)
plt.show()