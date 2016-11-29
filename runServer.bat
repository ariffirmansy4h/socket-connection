@echo off
cd target\
javac -d . ..\source\net\server\socket\*
java -cp . net.server.socket.Application
cd ..