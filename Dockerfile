# Use a base image otimizada
FROM eclipse-temurin:17-jdk as builder

# Configura o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Build do projeto
RUN ./gradlew build --no-daemon

# Produção
FROM eclipse-temurin:17-jre

# Configura o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado para o contêiner
COPY --from=builder /app/build/libs/*.jar app.jar

# Expõe a porta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
