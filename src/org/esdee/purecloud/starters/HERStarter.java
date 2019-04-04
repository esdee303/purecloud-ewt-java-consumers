package org.esdee.purecloud.starters;

import java.io.IOException;
import java.sql.SQLException;
import com.mypurecloud.sdk.v2.ApiException;

import org.esdee.purecloud.consumers.HerokuConsumer;
import org.esdee.purecloud.utilities.ArgumentGetter;
import org.json.simple.parser.ParseException;

public class HERStarter  {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ApiException, ParseException {
		HerokuConsumer.consumer(ArgumentGetter.HerQueue());
	}
}
