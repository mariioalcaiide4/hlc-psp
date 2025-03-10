import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
from xgboost import XGBRegressor

# ------- ACTIVIDAD 3 -------

# Cargar el CSV
df = pd.read_csv('all_seasons.csv')

# Procesar datos faltantes y rellenar valores faltantes
df = df.dropna()

# Seleccionar las características predictoras (X) y la variable objetivo (Y)

columnas_predictoras = ['pts', 'ast', 'reb', 'usg_pct', 'dreb_pct']
x = df[columnas_predictoras]
y = df['net_rating']

# Dividir entre entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

# Entrenar el modelo XGBoost
modelo_xgb = XGBRegressor(n_estimators=100, learning_rate=0.1, max_depth=5, random_state=42)
modelo_xgb.fit(X_train, y_train)

# Realizar predicciones
y_pred = modelo_xgb.predict(X_test)

# Prueba: Verificar tamaños
print(f"Tamaño de y_test: {len(y_test)}")
print(f"Tamaño de y_pred: {len(y_pred)}")

# Evaluar el modelo
mse = mean_squared_error(y_test, y_pred)
rmse = mse ** 0.5
print(f'Error RMSE: {rmse:.2f}')

# 🔥 SELECCIONAR SOLO 25 JUGADORES PARA LA GRÁFICA 🔥
y_test_sample = y_test.iloc[:25]  # Tomar solo los primeros 25 valores
y_pred_sample = y_pred[:25]  # Tomar las 25 primeras predicciones
player_ids = y_test_sample.index  # Obtener los índices (IDs de jugadores)

# 📊 Graficar las predicciones vs valores reales para 25 jugadores
plt.figure(figsize=(12, 6))
plt.scatter(player_ids, y_test_sample, color='blue', label='Valores Reales')
plt.scatter(player_ids, y_pred_sample, color='red', marker='x', label='Predicciones XGBoost')

# Configurar la gráfica
plt.xticks(rotation=45)  # Rotar etiquetas del eje X
plt.xlabel('ID del Jugador')
plt.ylabel('Net Rating')
plt.title('Predicción de Eficiencia Ofensiva (Net Rating) - 25 Jugadores')
plt.legend()
plt.grid(True)
plt.show()
