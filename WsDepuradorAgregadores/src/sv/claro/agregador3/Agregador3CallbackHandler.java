
/**
 * Agregador3CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package sv.claro.agregador3;

    /**
     *  Agregador3CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class Agregador3CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public Agregador3CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public Agregador3CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for saludar method
            * override this method for handling normal response from saludar operation
            */
           public void receiveResultsaludar(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saludar operation
           */
            public void receiveErrorsaludar(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for despedir method
            * override this method for handling normal response from despedir operation
            */
           public void receiveResultdespedir(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from despedir operation
           */
            public void receiveErrordespedir(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for darBaja method
            * override this method for handling normal response from darBaja operation
            */
           public void receiveResultdarBaja(
                    sv.claro.agregador3.Agregador3Stub.DarBajaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from darBaja operation
           */
            public void receiveErrordarBaja(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listaNegra method
            * override this method for handling normal response from listaNegra operation
            */
           public void receiveResultlistaNegra(
                    sv.claro.agregador3.Agregador3Stub.ListaNegraResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listaNegra operation
           */
            public void receiveErrorlistaNegra(java.lang.Exception e) {
            }
                


    }
    