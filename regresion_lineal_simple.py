import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

# ------- ACTIVIDAD 1 -------

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