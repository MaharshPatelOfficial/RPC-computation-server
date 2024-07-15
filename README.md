Implemented the following two operations using synchronous and asynchronous RPC:
  1. **Add(I,j)**
  2. **Sort (Array A)**

Have used Java’s RMI RPC to implement this part.
In **synchronous flow**, the client gets instance of remote object registered by server, calls the appropriate method and waits for the result.
In **asynchronous flow** the client gets instance of remote object registered by server, calls the appropriate method and starts doing other work and shows result when it receives it. ThreadPool is used to implement asynchronous flow.

There are four files involved in completing the above operations namely:
  1. **RMIClient.java** - This file consists of the main interface for the user on the client end. The client looks up for the server’s host registry, gets the instance of the remote object using the name associated with it and then invokes appropriate methods.
  2. **ICommon.java** - Serves as an interface common between the Client and Server
  3. **Common.java** - This file implements the “ICommon.java” file and has the core logic/code of the different operations to be performed
  4. **RMIServer.java** - Server class is responsible for creating remote object, exporting it and then associating it with a name in RMI registry
