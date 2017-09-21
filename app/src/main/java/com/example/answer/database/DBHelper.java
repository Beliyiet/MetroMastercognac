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

package com.example.answer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * 数据库管理辅助类，主要实现数据库的创建，版本更新。
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	
	//数据库名称
	static final String DB_NAME = "DB";
	static final int VERSION = 2;
	
	//题库数据
	static final String TABLE_NAME_TEST_LIBRARY = "table_test_library";
	static final String TEST_LIBRARY_COL_ID = "test_id";
	static final String TEST_LIBRARY_QUESTION_ID = "test_question_id";
	static final String TEST_LIBRARY_QUESTION_NAME = "test_question_name";
	static final String TEST_LIBRARY_QUESTION_TYPE = "test_question_type";
	static final String TEST_LIBRARY_QUESTION_ANSWER = "test_question_answer";
	static final String TEST_LIBRARY_QUESTION_ANALYSIS = "test_question_analysis";
	static final String TEST_LIBRARY_QUESTION_FOR = "test_question_for";
	static final String TEST_LIBRARY_QUESTION_SCORE = "test_question_score";
	static final String TEST_LIBRARY_QUESTION_OPTION_A = "test_question_option_a";
	static final String TEST_LIBRARY_QUESTION_OPTION_B = "test_question_option_b";
	static final String TEST_LIBRARY_QUESTION_OPTION_C = "test_question_option_c";
	static final String TEST_LIBRARY_QUESTION_OPTION_D = "test_question_option_d";
	
	//错误题数据
		static final String TABLE_NAME_MY_ERROR_QUESTION = "table_my_error_question";
		static final String MY_ERROR_QUESTION_ID = "_question_id";
		static final String MY_ERROR_QUESTION_NAME = "_question_name";
		static final String MY_ERROR_QUESTION_TYPE = "_question_type";
		static final String MY_ERROR_QUESTION_ANSWER = "_question_answer";
		static final String MY_ERROR_QUESTION_SELECTED = "_question_selected";
		static final String MY_ERROR_QUESTION_ISRIGHT = "_question_isright";
		static final String MY_ERROR_QUESTION_ANALYSIS = "_question_analysis";
		static final String MY_ERROR_QUESTION_OPTION_A = "_question_option_a";
		static final String MY_ERROR_QUESTION_OPTION_B = "_question_option_b";
		static final String MY_ERROR_QUESTION_OPTION_C = "_question_option_c";
		static final String MY_ERROR_QUESTION_OPTION_D = "_question_option_d";
		static final String MY_ERROR_QUESTION_OPTION_E = "_question_option_e";
		static final String MY_ERROR_QUESTION_OPTION_TYPE = "_question_option_type";
	
	
	/**
	 * 构造方法
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建题库基本数据表语句
		String cerateTable_testLibrary = "CREATE TABLE "
				+ TABLE_NAME_TEST_LIBRARY + " ("
				+ TEST_LIBRARY_COL_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ TEST_LIBRARY_QUESTION_ID + " TEXT,"
				+ TEST_LIBRARY_QUESTION_NAME + " TEXT,"
				+ TEST_LIBRARY_QUESTION_TYPE + " TEXT,"
				+ TEST_LIBRARY_QUESTION_ANSWER + " TEXT,"
				+ TEST_LIBRARY_QUESTION_FOR + " TEXT,"
				+ TEST_LIBRARY_QUESTION_SCORE + " TEXT,"
				+ TEST_LIBRARY_QUESTION_ANALYSIS + " TEXT,"
				+ TEST_LIBRARY_QUESTION_OPTION_A + " TEXT,"
				+ TEST_LIBRARY_QUESTION_OPTION_B + " TEXT,"
				+ TEST_LIBRARY_QUESTION_OPTION_C + " TEXT,"
				+ TEST_LIBRARY_QUESTION_OPTION_D + " TEXT)";
		

		String cerateTable_myErrorQuestion = "CREATE TABLE "
				+ TABLE_NAME_MY_ERROR_QUESTION + " ("
				+ MY_ERROR_QUESTION_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ MY_ERROR_QUESTION_NAME + " TEXT,"
				+ MY_ERROR_QUESTION_TYPE + " TEXT,"
				+ MY_ERROR_QUESTION_ANSWER + " TEXT,"
				+ MY_ERROR_QUESTION_SELECTED + " TEXT,"
				+ MY_ERROR_QUESTION_ISRIGHT + " TEXT,"
				+ MY_ERROR_QUESTION_ANALYSIS + " TEXT,"
				+ MY_ERROR_QUESTION_OPTION_A + " TEXT,"
				+ MY_ERROR_QUESTION_OPTION_B + " TEXT,"
				+ MY_ERROR_QUESTION_OPTION_C + " TEXT,"
				+ MY_ERROR_QUESTION_OPTION_D + " TEXT,"
				+ MY_ERROR_QUESTION_OPTION_E + " TEXT,"
				+ MY_ERROR_QUESTION_OPTION_TYPE + " TEXT)";
		
		
		db.execSQL(cerateTable_testLibrary);
		db.execSQL(cerateTable_myErrorQuestion);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
