/*******************************************************************************
 * Copyright © 2017 FMEBI.Corp System and CreateON Studio. All rights reserved.
 * Before using all the features of Metro Master (hereinafter referred to as MeM), please be sure to read and thoroughly understand this statement. You may choose not to use MeM, but if you use it, your use will be deemed to be a recognition of the entire contents of this statement.
 * Disclaimer: In view of MeM is currently not fully developed in the function, and the contents of the data information is limited by the information collected in the collection, processing errors may occur, the data is not entirely collected by the CreateON Studio, so the system of search / analysis The results are not responsible. The system does not assume any liability for any adverse consequences arising from the retrieval / analysis of the system as a basis for any commercial conduct or academic research.
 * About privacy: MeM does not currently collect personal privacy other than geographic location information about the user during use.
 * About copyright:
 * 1. All works of MeM that indicate "Metro Master", "Metro Master", "CreateON", "CreateON Studio", "" are owned by CreateON Studio and MeM. Other media, companies, organizations, websites or individuals may not be reproduced in any form, nor distorted and tampered with the contents of the system.
 * 2.MeM currently only granted to the Shanghai Fire Brigade special detachment of the new Jing squadron all, temporarily not granted any other units and personal use.
 * 3. Units authorized by the system shall not exceed the scope of authorization.
 * 4. The information provided by the system does not conform to the relevant paper text, subject to the paper text.
 * 5. If you are in contact with MeM due to the contents of the work, copyright and other issues, please do so within 30 days after MeM has released the work.
 * The right to interpret the system: the declaration of the system and its modification, renewal and final interpretation are owned by CreateON Studio and MeM.
 ******************************************************************************/

package com.example.answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.answer.adapter.MyErrorQuestionListAdapter;
import com.example.answer.bean.ErrorQuestion;
import com.example.answer.bean.ErrorQuestionInfo;
import com.example.answer.database.DBManager;
import com.example.answer.AnalogyExaminationActivity;
import com.fmebicorp.beliyiet.metromastercognac.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 我的错题
 *
 */
public class MyErrorQuestionActivity extends Activity {

	private ImageView left;
	private TextView title;

	public DBManager dbManager;

	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();// 列表数据
	private ListView listView;
	
	private List<ErrorQuestion> list=new ArrayList<ErrorQuestion>();

	private MyErrorQuestionListAdapter adapter;
	
	ErrorQuestion question;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_error_question);

		initView();

		FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab);
		fab2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
			    ///
			}
		});
	}

	private void initView() {
		left = (ImageView) findViewById(R.id.left);
		title = (TextView) findViewById(R.id.title);
		title.setText("我的错题");
		listView = (ListView) findViewById(R.id.listview);

		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		adapter = new MyErrorQuestionListAdapter(this, data, listView);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MyErrorQuestionActivity.this,MyErrorQuestionDetailActivity.class);
				question=list.get(position);
				intent.putExtra("questionName", question.getQuestionName());
				intent.putExtra("questionType", question.getQuestionType());
				intent.putExtra("questionAnswer", question.getQuestionAnswer());
				intent.putExtra("questionSelect", question.getQuestionSelect());
				intent.putExtra("isRight", question.getIsRight());
				intent.putExtra("Analysis", question.getAnalysis());
				intent.putExtra("optionA", question.getOptionA());
				intent.putExtra("optionB", question.getOptionB());
				intent.putExtra("optionC", question.getOptionC());
				intent.putExtra("optionD", question.getOptionD());
				intent.putExtra("optionE", question.getOptionE());
				intent.putExtra("optionType", question.getOptionType());
				startActivity(intent);
			}
		});
		
		DBManager dbManager = new DBManager(MyErrorQuestionActivity.this);
		dbManager.openDB();

		ErrorQuestionInfo[] errorQuestionInfos = dbManager.queryAllData();
		if (errorQuestionInfos == null) {
			Toast.makeText(MyErrorQuestionActivity.this, "暂无数据",
					Toast.LENGTH_SHORT).show();
		} else {
			Map<String, Object> map = null;
			for (int i = 0; i < errorQuestionInfos.length; i++) {
				ErrorQuestion errorQuestion=new ErrorQuestion();
				map = new HashMap<String, Object>();
				map.put("title", errorQuestionInfos[i].questionName);// 标题
				map.put("type", errorQuestionInfos[i].questionType);// 标题
				map.put("answer", errorQuestionInfos[i].questionAnswer);// 标题
				map.put("isright", errorQuestionInfos[i].isRight);// 
				map.put("selected", errorQuestionInfos[i].questionSelect);// 
				map.put("analysis", errorQuestionInfos[i].Analysis);// 
				data.add(map);
				
				errorQuestion.setQuestionName(errorQuestionInfos[i].questionName);
				errorQuestion.setQuestionType(errorQuestionInfos[i].questionType);
				errorQuestion.setQuestionAnswer(errorQuestionInfos[i].questionAnswer);
				errorQuestion.setQuestionSelect(errorQuestionInfos[i].questionSelect);
				errorQuestion.setIsRight(errorQuestionInfos[i].isRight);
				errorQuestion.setAnalysis(errorQuestionInfos[i].Analysis);
				errorQuestion.setOptionA(errorQuestionInfos[i].optionA);
				errorQuestion.setOptionB(errorQuestionInfos[i].optionB);
				errorQuestion.setOptionC(errorQuestionInfos[i].optionC);
				errorQuestion.setOptionD(errorQuestionInfos[i].optionD);
				errorQuestion.setOptionE(errorQuestionInfos[i].optionE);
				errorQuestion.setOptionType(errorQuestionInfos[i].optionType);
				list.add(errorQuestion);
			}
		}

	}

}
