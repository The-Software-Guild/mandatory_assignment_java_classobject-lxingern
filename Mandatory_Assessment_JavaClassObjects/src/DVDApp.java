import com.controller.DVDController;
import com.dao.DVDDao;
import com.dao.DVDDaoFileImpl;
import com.exceptions.UnableToLoadDvdsException;
import com.view.DVDView;
import com.view.DVDViewConsoleImpl;

public class DVDApp {

	public static void main(String[] args) {
		DVDView view = new DVDViewConsoleImpl();
		DVDDao dao;
		try {
			dao = new DVDDaoFileImpl("Mandatory_Assessment_JavaClassObjects/dvd.dat");			
		} catch (UnableToLoadDvdsException e) {
			System.out.println(e + " Exiting application.");
			return;
		}
		DVDController controller = new DVDController(view, dao);
		
		controller.run();
		
		System.out.println("See you again!");
	}

	
}
