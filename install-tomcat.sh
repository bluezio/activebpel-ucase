#!/bin/bash

set +x

if [ -z "$CATALINA_HOME" ]; then
    echo "Please set CATALINA_HOME to the Tomcat 5 installation directory." >&2
    exit 1
fi

DIST_P=org.activebpel.rt.dist
DEMO=dynamo-demo/tomcat
BPEL=PizzaDeliveryCompany
BPR="$BPEL.bpr"

mvn -am -pl "$DIST_P" clean install
tar -xzf "$DIST_P"/target/*-tomcat.tar.gz -C "$CATALINA_HOME"

# Replace "80" by "70" in server.xml, to change the port Tomcat listens to
sed -ie 's/70/80/g' "$CATALINA_HOME/conf/server.xml"

# Copy over the Tomcat .war files
cp -v "$DEMO"/*.war "$CATALINA_HOME/webapps"

# Zip the composition and install it

pushd "$DEMO/$BPEL"
zip -r "$BPR" bpel META-INF wsdl
cp -v "$BPR" "$CATALINA_HOME/bpr"
popd
