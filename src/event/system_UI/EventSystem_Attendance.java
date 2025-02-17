package event.system_UI;
public class EventSystem_Attendance {

    public static void main(String[] args) {
        // TODO code application logic here
        
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);
        try {
            for(int i = 0; i <= 100; ++i) {
                Thread.sleep(10);
                splash.progressPercentage.setText(Integer.toString(i)+"%");
                splash.progressBar.setValue(i);

                
                if(i == 100) {
                    splash.dispose();
                    LoginPage login = new LoginPage();
                    login.setVisible(true);
                }
            }
        } catch (Exception e){
            System.out.println("Error");
        }
    }
    
}