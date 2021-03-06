package com.cmu.scout.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cmu.scout.R;
import com.cmu.scout.provider.ScoutContract.Matches;
import com.cmu.scout.provider.ScoutContract.TeamMatches;
import com.cmu.scout.provider.ScoutContract.Teams;
import com.cmu.scout.ui.MatchPagerActivity;

public class MatchInputAutoFragment extends MatchFragment {
	
//	private static final String TAG = "MatchInputAutoFragment";
//	private static final boolean DEBUG = true;
	
	public static final int EDIT_TEXT_NONE = 0;
	public static final int EDIT_TEXT_HIGH = 1;
	public static final int EDIT_TEXT_MED = 2;
	public static final int EDIT_TEXT_LOW = 3;
	
	public int faultyEditTextBox = EDIT_TEXT_NONE;
	
	private EditText mAutoHighCounter;
	private EditText mAutoHighMissCounter; 
	private EditText mAutoMedCounter;
	private EditText mAutoMedMissCounter;
	private EditText mAutoLowCounter;
	private EditText mAutoLowMissCounter;
	/*
	private static final String AUTO_HIGH_MADE_STORAGE_KEY = "auto_high_made";
	private static final String AUTO_MED_MADE_STORAGE_KEY = "auto_med_made";
	private static final String AUTO_LOW_MADE_STORAGE_KEY = "auto_low_made";
	private static final String AUTO_HIGH_ATTEMPT_STORAGE_KEY = "auto_high_attempt";
	private static final String AUTO_MED_ATTEMPT_STORAGE_KEY = "auto_med_attempt";
	private static final String AUTO_LOW_ATTEMPT_STORAGE_KEY = "auto_low_attempt";
	
	private static final String SUMMARY_AUTO_MADE_STORAGE_KEY = "summary_auto_made";
	private static final String SUMMARY_AUTO_ATTEMPT_STORAGE_KEY = "summary_auto_attempt";
	private static final String SUMMARY_AUTO_POINTS_STORAGE_KEY = "summary_auto_points";
	*/
	private int mAutoHighMadeInit;
	private int mAutoMedMadeInit;
	private int mAutoLowMadeInit;
	private int mAutoHighAttemptInit;
	private int mAutoMedAttemptInit;
	private int mAutoLowAttemptInit;
	
	private int mSummaryAutoNumScoredInit;
	private int mSummaryAutoNumAttemptInit;
	private int mSummaryAutoNumPointsInit;
	
	private static final String[] PROJECTION = { 
		TeamMatches.AUTO_NUM_SCORED_HIGH, 
		TeamMatches.AUTO_NUM_SCORED_MED,
		TeamMatches.AUTO_NUM_SCORED_LOW,
		TeamMatches.AUTO_NUM_ATTEMPT_HIGH,
		TeamMatches.AUTO_NUM_ATTEMPT_MED,
		TeamMatches.AUTO_NUM_ATTEMPT_LOW
	};

	public static MatchInputAutoFragment newInstance() {
//		if (DEBUG) Log.v(TAG, "newInstance()");
		return new MatchInputAutoFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		if (DEBUG) Log.v(TAG, "++ ON CREATE VIEW ++");
		/*
		if (savedInstanceState != null) {	
			mAutoHighMadeInit = savedInstanceState.getInt(AUTO_HIGH_MADE_STORAGE_KEY);
			mAutoMedMadeInit = savedInstanceState.getInt(AUTO_MED_MADE_STORAGE_KEY);
			mAutoLowMadeInit = savedInstanceState.getInt(AUTO_LOW_MADE_STORAGE_KEY);
			mAutoHighAttemptInit = savedInstanceState.getInt(AUTO_HIGH_ATTEMPT_STORAGE_KEY);
			mAutoMedAttemptInit = savedInstanceState.getInt(AUTO_MED_ATTEMPT_STORAGE_KEY);
			mAutoLowAttemptInit = savedInstanceState.getInt(AUTO_LOW_ATTEMPT_STORAGE_KEY);
			
			mSummaryAutoNumScoredInit = savedInstanceState.getInt(SUMMARY_AUTO_MADE_STORAGE_KEY);
			mSummaryAutoNumAttemptInit = savedInstanceState.getInt(SUMMARY_AUTO_ATTEMPT_STORAGE_KEY);
			mSummaryAutoNumPointsInit = savedInstanceState.getInt(SUMMARY_AUTO_POINTS_STORAGE_KEY);
		}*/

		final View parent = inflater.inflate(R.layout.match_scout_auto_page, container, false);
		
		mAutoHighCounter = (EditText) parent.findViewById(R.id.ET_Auto_Shots_Hit_High);
		mAutoMedCounter = (EditText) parent.findViewById(R.id.ET_Auto_Shots_Hit_Med);
		mAutoLowCounter = (EditText) parent.findViewById(R.id.ET_Auto_Shots_Hit_Low);
		
		mAutoHighMissCounter = (EditText) parent.findViewById(R.id.ET_Auto_Shots_Miss_High);
		mAutoMedMissCounter = (EditText) parent.findViewById(R.id.ET_Auto_Shots_Miss_Med);
		mAutoLowMissCounter = (EditText) parent.findViewById(R.id.ET_Auto_Shots_Miss_Low);
		
		return parent;
	}
/*
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (DEBUG) Log.v(TAG, "ON SAVE INSTANCE STATE");
		
		outState.putInt(AUTO_HIGH_MADE_STORAGE_KEY, mAutoHighMadeInit);
		outState.putInt(AUTO_MED_MADE_STORAGE_KEY, mAutoMedMadeInit);
		outState.putInt(AUTO_LOW_MADE_STORAGE_KEY, mAutoLowMadeInit);
		outState.putInt(AUTO_HIGH_ATTEMPT_STORAGE_KEY, mAutoHighAttemptInit);
		outState.putInt(AUTO_MED_ATTEMPT_STORAGE_KEY, mAutoMedAttemptInit);
		outState.putInt(AUTO_LOW_ATTEMPT_STORAGE_KEY, mAutoLowAttemptInit);
		
		outState.putInt(SUMMARY_AUTO_MADE_STORAGE_KEY, mSummaryAutoNumScoredInit);
		outState.putInt(SUMMARY_AUTO_ATTEMPT_STORAGE_KEY, mSummaryAutoNumAttemptInit);
		outState.putInt(SUMMARY_AUTO_POINTS_STORAGE_KEY, mSummaryAutoNumPointsInit);
	}
*/
	@Override
	public void updateDisplay(int viewId) {
//		if (DEBUG) Log.v(TAG, "updateDisplay()");
		
		switch(viewId) {
		case R.id.BT_Auto_Shots_Hit_High:
			incCount(mAutoHighCounter);
			break;
		case R.id.BT_Auto_Shots_Hit_Med:
			incCount(mAutoMedCounter);
			break;
		case R.id.BT_Auto_Shots_Hit_Low:
			incCount(mAutoLowCounter);
			break;
		case R.id.BT_Auto_Shots_Miss_High:
			incCount(mAutoHighMissCounter);
			break;
		case R.id.BT_Auto_Shots_Miss_Med:
			incCount(mAutoMedMissCounter);
			break;
		case R.id.BT_Auto_Shots_Miss_Low:
			incCount(mAutoLowMissCounter);
			break;
		}
	}
	
	private static final String[] SUMMARY_PROJ = {
		Teams.SUMMARY_AUTO_NUM_ATTEMPT,
		Teams.SUMMARY_AUTO_NUM_SCORED,
		Teams.SUMMARY_AUTO_NUM_POINTS
	};
	
	@Override
	public void saveData() {
//		if (DEBUG) Log.v(TAG, "saveData()");
		
		int teamId = ((MatchPagerActivity)getActivity()).getTeamId();
		int matchId = ((MatchPagerActivity)getActivity()).getMatchId();
		
		// retrieve data from screen
		String highShotsMade = mAutoHighCounter.getText().toString();
		String medShotsMade  = mAutoMedCounter.getText().toString();
		String lowShotsMade  = mAutoLowCounter.getText().toString();
		String highShotsMiss = mAutoHighMissCounter.getText().toString();
		String medShotsMiss  = mAutoMedMissCounter.getText().toString();
		String lowShotsMiss  = mAutoLowMissCounter.getText().toString();
		
		int numHighShotsMade = (highShotsMade == null || highShotsMade.length() == 0) ? 0 : Integer.valueOf(highShotsMade);
		int numMedShotsMade  = (medShotsMade == null  || medShotsMade.length() == 0)  ? 0 : Integer.valueOf(medShotsMade);
		int numLowShotsMade  = (lowShotsMade == null  || lowShotsMade.length() == 0)  ? 0 : Integer.valueOf(lowShotsMade);
		int numHighShotsMiss = (highShotsMiss == null || highShotsMiss.length() == 0) ? 0 : Integer.valueOf(highShotsMiss);
		int numMedShotsMiss  = (medShotsMiss == null  || medShotsMiss.length() == 0)  ? 0 : Integer.valueOf(medShotsMiss);
		int numLowShotsMiss  = (lowShotsMiss == null  || lowShotsMiss.length() == 0)  ? 0 : Integer.valueOf(lowShotsMiss);
		
		// compute summary data offset
		int summaryAutoNumScored = (numHighShotsMade + numMedShotsMade + numLowShotsMade) - mSummaryAutoNumScoredInit;
		int summaryAutoNumAttempt = (numHighShotsMiss + numMedShotsMiss + numLowShotsMiss) 
			+(numHighShotsMade + numMedShotsMade + numLowShotsMade) - mSummaryAutoNumAttemptInit;
		int summaryAutoNumPoints = (3*numHighShotsMade + 2*numMedShotsMade + 1*numLowShotsMade) - mSummaryAutoNumPointsInit;
		
		// get already existing cumulative data
		final Uri summaryUri = Teams.buildTeamIdUri(""+teamId);
		final Cursor summaryCur = getActivity().getContentResolver().query(summaryUri, SUMMARY_PROJ, null, null, null);
		
		if (summaryCur != null && summaryCur.moveToFirst()) {
			summaryAutoNumScored += summaryCur.getInt(summaryCur.getColumnIndex(Teams.SUMMARY_AUTO_NUM_SCORED));
			summaryAutoNumAttempt += summaryCur.getInt(summaryCur.getColumnIndex(Teams.SUMMARY_AUTO_NUM_ATTEMPT));
			summaryAutoNumPoints += summaryCur.getInt(summaryCur.getColumnIndex(Teams.SUMMARY_AUTO_NUM_POINTS));			
			summaryCur.close();
		}
		
		ContentValues teamMatchValues = new ContentValues();
		ContentValues summaryValues = new ContentValues();
		
		// add team-match data
		//teamMatchValues.put(TeamMatches.TEAM_ID, teamId);
		//teamMatchValues.put(TeamMatches.MATCH_ID, matchId);
		teamMatchValues.put(TeamMatches.AUTO_NUM_SCORED_HIGH, numHighShotsMade);
		teamMatchValues.put(TeamMatches.AUTO_NUM_SCORED_MED, numMedShotsMade);
		teamMatchValues.put(TeamMatches.AUTO_NUM_SCORED_LOW, numLowShotsMade);
		teamMatchValues.put(TeamMatches.AUTO_NUM_ATTEMPT_HIGH, numHighShotsMiss + numHighShotsMade);
		teamMatchValues.put(TeamMatches.AUTO_NUM_ATTEMPT_MED, numMedShotsMiss + numMedShotsMade);
		teamMatchValues.put(TeamMatches.AUTO_NUM_ATTEMPT_LOW, numLowShotsMiss + numLowShotsMade);
		
		// add summary data
		summaryValues.put(Teams.SUMMARY_AUTO_NUM_SCORED, summaryAutoNumScored);
		summaryValues.put(Teams.SUMMARY_AUTO_NUM_ATTEMPT, summaryAutoNumAttempt);
		summaryValues.put(Teams.SUMMARY_AUTO_NUM_POINTS, summaryAutoNumPoints);
		
		getActivity().getContentResolver().update(Matches.buildMatchIdTeamIdUri(""+matchId, ""+teamId), teamMatchValues, null, null);
		getActivity().getContentResolver().update(summaryUri, summaryValues, null, null);
	}
	
	@Override
	public void loadData() {
//		if (DEBUG) Log.v(TAG, "loadData()");
		
		int teamId = ((MatchPagerActivity)getActivity()).getTeamId();
		int matchId = ((MatchPagerActivity)getActivity()).getMatchId();

		Uri teamUri = Matches.buildMatchIdTeamIdUri(""+matchId, ""+teamId);
		final Cursor cur = getActivity().getContentResolver().query(teamUri, PROJECTION, null, null, null);
		
		if (cur != null && cur.moveToFirst()) {
			int numHighShotsMade = cur.getInt(cur.getColumnIndex(TeamMatches.AUTO_NUM_SCORED_HIGH));
			int numMedShotsMade  = cur.getInt(cur.getColumnIndex(TeamMatches.AUTO_NUM_SCORED_MED));
			int numLowShotsMade  = cur.getInt(cur.getColumnIndex(TeamMatches.AUTO_NUM_SCORED_LOW));
			int numHighShotsAtmp = cur.getInt(cur.getColumnIndex(TeamMatches.AUTO_NUM_ATTEMPT_HIGH));
			int numMedShotsAtmp  = cur.getInt(cur.getColumnIndex(TeamMatches.AUTO_NUM_ATTEMPT_MED));
			int numLowShotsAtmp  = cur.getInt(cur.getColumnIndex(TeamMatches.AUTO_NUM_ATTEMPT_LOW));
		
			mAutoHighCounter.setText("" + numHighShotsMade);
			mAutoMedCounter.setText("" + numMedShotsMade);
			mAutoLowCounter.setText("" + numLowShotsMade);
			mAutoHighMissCounter.setText("" + (numHighShotsAtmp - numHighShotsMade));
			mAutoMedMissCounter.setText("" + (numMedShotsAtmp - numMedShotsMade));
			mAutoLowMissCounter.setText("" + (numLowShotsAtmp - numLowShotsMade));
		}
		cur.close();
	}

	@Override
	public void clearScreen() {
//		if (DEBUG) Log.v(TAG, "clearScreen()");
		
		mAutoHighCounter.setText(R.string.zero);
		mAutoHighMissCounter.setText(R.string.zero);
		mAutoMedCounter.setText(R.string.zero);
		mAutoMedMissCounter.setText(R.string.zero);
		mAutoLowCounter.setText(R.string.zero);
		mAutoLowMissCounter.setText(R.string.zero);
	}
	
    public void incCount(EditText et){
    	String scoreStr = et.getText().toString();
		int score = (scoreStr == null || scoreStr.length() == 0) ? 0 : Integer.valueOf(scoreStr);
		score = Math.min(score+1, MatchPagerActivity.MAX_SCORE);
		et.setText("" + score);
    }
    
	@Override 
	public void onResume() {
		super.onResume();
		loadData();
		
		// store initial data so we don't add to cumulative data when we shouldn't be
		String highShotsMade = mAutoHighCounter.getText().toString();
		String medShotsMade  = mAutoMedCounter.getText().toString();
		String lowShotsMade  = mAutoLowCounter.getText().toString();
		String highShotsMiss = mAutoHighMissCounter.getText().toString();
		String medShotsMiss  = mAutoMedMissCounter.getText().toString();
		String lowShotsMiss  = mAutoLowMissCounter.getText().toString();
		
		mAutoHighMadeInit = (!TextUtils.isEmpty(highShotsMade)) ? Integer.valueOf(highShotsMade) : 0;
		mAutoMedMadeInit = (!TextUtils.isEmpty(medShotsMade)) ? Integer.valueOf(medShotsMade) : 0;
		mAutoLowMadeInit = (!TextUtils.isEmpty(lowShotsMade)) ? Integer.valueOf(lowShotsMade) : 0;
		mAutoHighAttemptInit = ((!TextUtils.isEmpty(highShotsMiss)) ? Integer.valueOf(highShotsMiss) : 0) + mAutoHighMadeInit;
		mAutoMedAttemptInit = ((!TextUtils.isEmpty(medShotsMiss)) ? Integer.valueOf(medShotsMiss) : 0) + mAutoMedMadeInit;
		mAutoLowAttemptInit = ((!TextUtils.isEmpty(lowShotsMiss)) ? Integer.valueOf(lowShotsMiss) : 0) + mAutoLowMadeInit;
		
		mSummaryAutoNumScoredInit = (mAutoHighMadeInit + mAutoMedMadeInit + mAutoLowMadeInit);
		mSummaryAutoNumAttemptInit = (mAutoHighAttemptInit + mAutoMedAttemptInit + mAutoLowAttemptInit);
		
		mSummaryAutoNumPointsInit = (3*mAutoHighMadeInit + 2*mAutoMedMadeInit + 1*mAutoLowMadeInit);
	}
	
	@Override
	public void onPause() {
		super.onPause();		
		saveData();
	}
}
