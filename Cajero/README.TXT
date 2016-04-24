Sistema de Cajero Electronico de un Banco
(C) Guillermo Jimenez 2016.

Este ejemplo muestra la implementacion parcial de un sistema de cajero
electronico en el servidor de aplicaciones Tomcat, accediendo a una base de
datos en MySQL. (Los comandos siguientes son para Windows.)

Para ponerlo en operación se requieren varios pasos.
0. Se asume que se tiene instalada la version mas reciente de Java.
1.  Instalar la version mas reciente de MySQL
2.  Crear la base de datos CAJERO con la tabla 'cuenta', conteniendo los campos
       (se puede utilizar una herramienta como navicat (www.navicat.com) 
      -idcuenta: integer (key)
      -nombre: string (longitud 30)
      -saldo: float (2 decimales)
3.  Agregar por lo menos un registro a la tabla de 'cuenta'
4.  Instalar la version mas reciente de Tomcat.
5.  Copiar mysql-connector-java-5.1.38-bin.jar dentro de C:\...Tomcat\lib
6.  Agregar a CLASSPATH el archivo C:\...Tomcat\lib\mysql-connector-java-5.1.38-bin.jar
7.  Agregar a CLASSPATH el archivo C:\...Tomcat\lib\servlet-api.jar
8.  Agregar a CLASSPATH el directorio C:\...Tomcat\webapps\Cajero\WEB-INF\classes
--  Desempacar el archivo 'cajero.zip' dentro del directorio Tomcat\webapps
9.  Compilar utilizando el comando (en una ventana de Command): 
      C:\..\webapps\Cajero\Entidades> javac -d ..\WEB-INF\classes *.java
      C:\..\webapps\Cajero\Controles> javac -d ..\WEB-INF\classes *.java
      C:\..\webapps\Cajero\Interfaces> javac -d ..\WEB-INF\classes *.java
10. Iniciar la ejecucion de Tomcat (C:\...Tomcat\bin\startup.bat).
11. Indicar en el navegador Web el URL:
    http://localhost:8080/Cajero/

NOTAS ADICIONALES:
-El archivo de configuracion 'web.xml' especifica los Servlets que seran desplegados
-Al modificar CLASSPATH asegurarse de dar toda la trayectoria desde C:\...
-El código en Entidades asume que en MySQL: user=root y password=admin 
-Cada vez que hagas cambios a un archivo dentro de webapps debes reiniciar Tomcat
-   y asegurarte de dar "refresh" o "reload" en el navegador al iniciar el acceso
-   a la aplicación. 