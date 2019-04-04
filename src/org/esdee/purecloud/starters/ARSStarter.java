package org.esdee.purecloud.starters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import com.mypurecloud.sdk.v2.ApiException;

import org.esdee.purecloud.consumers.ARSConsumer;

public class ARSStarter extends TimerTask {

	public void run() {
		try {
			ARSConsumer.consumer();
		} catch (ClassNotFoundException | SQLException | IOException | ApiException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ApiException {
		Timer timer = new Timer();
		timer.schedule(new ARSStarter(), 0,30000);
	}
}
