package com.ivr.export;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import com.mypurecloud.sdk.v2.ApiClient;
import com.mypurecloud.sdk.v2.ApiResponse;
import com.mypurecloud.sdk.v2.Configuration;
import com.mypurecloud.sdk.v2.PureCloudRegionHosts;
import com.mypurecloud.sdk.v2.api.ArchitectApi;
import com.mypurecloud.sdk.v2.extensions.AuthResponse;
import com.mypurecloud.sdk.v2.model.DataTable;
import com.mypurecloud.sdk.v2.model.DataTableExportJob;
import com.mypurecloud.sdk.v2.model.DataTablesDomainEntityListing;

public class Exportdata {

	public static void main(String[] args) {
		download();
	}

	public static void download() {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat DateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		ApiClient apiClient = null;
		String clientid = "2aabd7ae-7cb3-4b3f-9383-9c7ac1daa244";
		String secretid = "IRKzT7n3KhTD8_0PAPHRNA_fbXM6NX9iFw-d1hFDAXs";
		String filepath = "F:\\IVR_Monitering\\Download\\table\\";
		List<String> table = Arrays.asList("PRU_JP_POJ_ANI_LIST_STATE", "PRU_JP_POJ_DATA_ACTION_STATE",
				"PRU_JP_POJ_DNIS_MAPPING", "PRU_JP_POJ_INPUT_STATE", "PRU_JP_POJ_MENU_STATE", "PRU_JP_POJ_PROMPT_STATE",
				"PRU_JP_POJ_QUEUE_DATA", "PRU_JP_POJ_ROUTING_DATA", "PRU_JP_POJ_SCHEDULE", "PRU_JP_POJ_STATE_DETAILS");

		Map<String, String> tableMap = new HashMap<>();

		boolean iteration = true;
		int currentPageSize = 0;
		Integer pageNumber = 1;
		Integer pageSize = 100;
		String sortBy = "name";
		String sortOrder = "ascending";

		try {
			apiClient = ApiClient.Builder.standard().withBasePath(PureCloudRegionHosts.us_west_2).build();
			ApiResponse<AuthResponse> authResponse = apiClient.authorizeClientCredentials(clientid, secretid);
			Configuration.setDefaultApiClient(apiClient);
			
			System.out.println("[Pointel] : download()  Genesys Connected Successfully");
			ArchitectApi apiInstance = new ArchitectApi();

			while (iteration) {
				DataTablesDomainEntityListing dataTables = apiInstance.getFlowsDatatables(null, pageNumber, pageSize,
						sortBy, sortOrder, null, null);

				List<DataTable> list = dataTables.getEntities();
				currentPageSize = list.size();

				for (DataTable data : list) {
					if (table.contains(data.getName())) {
						tableMap.put(data.getName(), data.getId());
					}
				}
				if (currentPageSize == pageSize) {
					pageNumber += 1;
				} else {
					iteration = false;
				}

			}
			
			for (String tablename : table) {
				System.out.println("[Pointel]: download () Table Name " + tablename);

				// get the id from map
				String dataTableId = tableMap.get(tablename);

				DataTableExportJob result = apiInstance.postFlowsDatatableExportJobs(dataTableId);
				String exportJobId = result.getId();
				System.out.println(exportJobId);
				Thread.sleep(3000);

				// get the download Details
				DataTableExportJob downloadDetails = apiInstance.getFlowsDatatableExportJob(dataTableId, exportJobId);
				// System.out.println(downloadDetails);
				if (downloadDetails.getDownloadURI() == null
						&& downloadDetails.getStatus().equals(downloadDetails.getStatus().PROCESSING)) {
					Thread.sleep(3000);
				} else {

					// Get the downloadURI from genesys
					String csvDownloadURI = downloadDetails.getDownloadURI();
					// System.out.println(csvDownloadURI);
					
					// Format the current timeD
					String startTime = DateFormate.format(new Date().getTime());

					System.out.println("[Pointel]: download () Data Table Download Start Time " + startTime);

					// Pass the value from download path and Token
					new Exportdata().downloadCsvFile(csvDownloadURI, filepath + tablename + ".csv",
							authResponse.getBody().getAccess_token());

					String endTime = DateFormate.format(new Date().getTime());
					System.out.println("Pointel]: download () Data Table Download EndTime " + endTime);
					Date startDate = DateFormate.parse(startTime);
					Date endDate = DateFormate.parse(endTime);
					long timeDiffer = endDate.getTime() - startDate.getTime();
					long second = TimeUnit.SECONDS.convert(timeDiffer, TimeUnit.MILLISECONDS);
					long minutes = TimeUnit.MINUTES.convert(timeDiffer, TimeUnit.MILLISECONDS);
					long millisecond = TimeUnit.MILLISECONDS.convert(timeDiffer, TimeUnit.MILLISECONDS);
					System.out.println("[Pointel]: download () Data Table Download  Minutes : " + minutes + " second : "
							+ second + " millisecond :" + millisecond);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void downloadCsvFile(String downloadURL, String destinationPath, String accessToken) {

		try {
			URL url = new URL(downloadURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", "bearer " + accessToken);
			connection.setRequestMethod("GET");
			
			try (InputStream in = connection.getInputStream();
					OutputStream out = new BufferedOutputStream(Files.newOutputStream(Paths.get(destinationPath)))) {

				byte[] buffer = new byte[8192];
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				
				System.out.println("[Pointel]: downloadCsvFile () File downloaded successfully " + destinationPath);	
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {				
				System.out.println("[Pointel]: downloadCsvFile ()  successfully HttpURLConnection disconnect " );	
				connection.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
