package client.app;
import client.view.UserInterface;

public class RunApp{
    public static void main(String[] args){
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.returnFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
