/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package event.system_attendance;

/** 
 *
 * @author Konoe
 */
public class EventSystem_Attendance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);
        try {
            for(int i = 0; i <= 100; ++i) {
                Thread.sleep(60);
                splash.progressPercentage.setText(Integer.toString(i)+"%");
                splash.progressBar.setValue(i);

                
                if(i == 100) {
                    splash.dispose();
                    Login login = new Login();
                    login.setVisible(true);
                }
            }
        } catch (Exception e){
            System.out.println("Error");
        }
    }
    
}
