FROM quay.io/wildfly/wildfly:latest

# Criar usuário admin para porta 9990
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin123 --silent

# Copiar deploy
COPY backend-module/target/backend-module-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments
COPY ejb-module/target/ejb-module-1.0.0.jar /opt/jboss/wildfly/standalone/deployments

# Expor portas
EXPOSE 8080 9990

# Iniciar em modo standalone
#CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
