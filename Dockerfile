FROM payara/server-web
ENV APPNAME app
ENV DOMAIN domain1
ENV AUTODEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN}
COPY target/${APPNAME}.war  ${AUTODEPLOY}