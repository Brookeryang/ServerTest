package com.bk.soft.servertest.test;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bk.soft.servertest.R;

/**
 * 错误报告显示页
 * @author 王端晴
 *
 */
public class ReportPage extends Activity {

	public static final String REPORT_CONTENT = "content";

	protected StringBuilder reportTitle;
	protected StringBuilder reportContent;
//	private TextView reportTextview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String bugContent = getIntent().getStringExtra(
				REPORT_CONTENT);
		buildContent(bugContent);
		this.setContentView(R.layout.activity_report);
//		reportTextview = (TextView) this.findViewById(R.id.report_text);
//		reportTextview.setText(reportContent.toString());
	}

	private void buildContent(String bugContent) {
		reportContent = new StringBuilder();
		reportContent
				.append("Application has been crached, sorry."
						+ " You can help to fix this bug by"
						+ " sending the report below to developers."
						+ " The report will be sent by e-mail. Thank you in advance!\n\n");

		reportContent.append("Model:").append(Build.MODEL).append("\n");
		reportContent.append("Device:").append(Build.DEVICE).append("\n");
		reportContent.append("Product:").append(Build.PRODUCT).append("\n");
		reportContent.append("Manufacturer:").append(Build.MANUFACTURER)
				.append("\n");
		reportContent.append("Version:").append(Build.VERSION.RELEASE)
				.append("\n");
		reportContent.append(bugContent);
	}
	
	public void send(View view) {
		if(view.getId() == R.id.send) {
			finish();
		}
//		else if(view.getId() == R.id.send) {
//			//send your bug content to server,then finish this activity
//			finish();
//		}
	}
}
