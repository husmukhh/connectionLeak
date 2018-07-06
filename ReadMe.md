This utility program is taken from blog https://vladmihalcea.com/the-best-way-to-detect-database-connection-leaks/.
Modified this program to make it as a utilty instead of junit test case. Not followed good programming pracitice but tested this thoroughly.
You need to add main method in class ConnectionLeakTest and call assertNoLeaks(). Or you can make it junit testcase. just put  @Test annotaion on the
assertNoLeaks() method of class ConnectionLeakTest and run it as junit test.

In private method newConnection() of ConnectionLeakUtil you need to provide your database server details.
1- jdbc url
2- username
3- password

Can make it configureable if you always want to make sure about your application's connection leak status.

