package org.esdee.purecloud.starters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import com.mypurecloud.sdk.v2.ApiException;

import org.esdee.purecloud.consumers.QMConsumer;
import org.esdee.purecloud.utilities.ArgumentGetter;

public class QMStarter extends TimerTask {
		
	public void run() {
		try {
			QMConsumer.consumer(ArgumentGetter.QmQueue());	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ApiException {
		Timer timer = new Timer();
		timer.schedule(new QMStarter(), 0,10000);
	}
}