package client.app.interfaces;

/*
*Interface to be implemented by any class that has persistence in the application. Implements record() function to convert it's running state
*into XML format for recording. Implements load() to read XML format and initialize all run time variables.
*/
interface Recordable{
    Element record(); //converts the object into an XML DOM Element Node
    void load(Element root); //loads runtime information given an XML DOM Element Node.
}
