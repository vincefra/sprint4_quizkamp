/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

/**
 *
 * @author xingao
 */
public class ServerProtocol {
   private static final int WAITING = 0;
   private static final int S1JOINED = 1;
   private static final int S1WAITING = 2;
   private static final int S1S2JOINED = 3; 
   private static final int SENTCATALOG = 0;
   private static final int S1SELECTEDCATALOG = 1;
   private static final int SENTQUESTIONs = 2;
   private static final int ANOTHER = 3;
}
