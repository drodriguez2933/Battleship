import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Battleship extends JFrame{
	
	JFrame field;
	JPanel battlefield;
	JPanel scoreboard;
	JLabel clickNum;
	String clicks = "0";
	
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	int[] xy1 = new int[6];
	int[] xy2 = new int[4];
	int[] xy3 = new int[2];
	int[] check = new int[36];
	int[] check1 = new int[36];
	int[] check2 = new int[36];
	
	
	int[] acheck = new int[36];
	int[] dcheck = new int[36];
	int[] ccheck = new int[36];
	int[] missCheck = new int[36];
	
	int cruiser;
	int destroyer;
	int aircraftCarrier;
	int winCount = 0;
	int count = 0; // counts number of clicks
	
	JPanel score;
	JLabel hitMiss;
	
	JRadioButton csunk;
	JRadioButton cfloat;
	JRadioButton dsunk;
	JRadioButton dfloat;
	JRadioButton asunk;
	JRadioButton afloat;
	
	
	JPanel a1;
	JPanel b1;
	JPanel c1;
	JPanel d1;
	JPanel e1;
	JPanel f1;
	
	JPanel a2;
	JPanel b2;
	JPanel c2;
	JPanel d2;
	JPanel e2;
	JPanel f2;
	
	JPanel a3;
	JPanel b3;
	JPanel c3;
	JPanel d3;
	JPanel e3;
	JPanel f3;
	
	JPanel a4;
	JPanel b4;
	JPanel c4;
	JPanel d4;
	JPanel e4;
	JPanel f4;
	
	JPanel a5;
	JPanel b5;
	JPanel c5;
	JPanel d5;
	JPanel e5;
	JPanel f5;
	
	JPanel a6;
	JPanel b6;
	JPanel c6;
	JPanel d6;
	JPanel e6;
	JPanel f6;
	
	Random rn = new Random();
	
	
	public Battleship()
	{
		field = new JFrame();
		field.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		field.setSize(900,700);
		field.setVisible(true);
		field.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		e.gridx = 0;
		e.gridy = 0;
		e.ipadx = 300;
		e.ipady = 300;
		
		battlefield = new JPanel(new GridLayout(6,6));
		battlefield.setSize(600,400);
		
		
		
		
		buildBattlefield();
		setShips();
		checkHit();
		cheat();
		field.add(battlefield, e);
		
		
		score = new JPanel(new GridBagLayout());
		score.setBackground(Color.WHITE);
		score.setBorder(border);
		e.gridx = 0;
		e.gridy = 1;
		e.ipadx = 600;
		e.ipady = 50;
		e.insets = new Insets(10,0,0,0);
		buildScoreboard();
		field.add(score, e);
		
		
		battlefield.addMouseListener(new clicked());
		addPaneClicks();
		
		
	}
	
	public void buildScoreboard(){
		csunk = new JRadioButton();
		cfloat = new JRadioButton();
		asunk = new JRadioButton();
		afloat = new JRadioButton();
		dsunk = new JRadioButton();
		dfloat = new JRadioButton();
		
		cfloat.setEnabled(false);
		csunk.setEnabled(false);
		dfloat.setEnabled(false);
		dsunk.setEnabled(false);
		afloat.setEnabled(false);
		asunk.setEnabled(false);
		
		cfloat.setSelected(true);
		dfloat.setSelected(true);
		afloat.setSelected(true);
		
		GridBagConstraints a = new GridBagConstraints();
		
		a.insets = new Insets(10,10,10,10);
		
		JLabel cruiserLabel = new JLabel("Cruiser: ");
		JLabel destroyerLabel = new JLabel("Destroyer: ");
		JLabel aircraftCarrierLabel = new JLabel("Aircraft Carrier: ");
		
		JLabel space = new JLabel(" ");
		JLabel alive = new JLabel("Ship Afloat");
		JLabel sunk = new JLabel("Ship Sunk");
		
		JLabel clickNumLabel = new JLabel("Number of Clicks:");
		clickNum = new JLabel(clicks);
		
		a.anchor = GridBagConstraints.LINE_START;
		
		a.gridy = 0;
		a.gridx = 0;
		score.add(space,a);
		
		a.gridy = 0;
		a.gridx = 1;
		a.anchor = GridBagConstraints.CENTER;
		score.add(alive,a);
		
		a.gridy = 0;
		a.gridx = 2;
		a.anchor = GridBagConstraints.CENTER;
		score.add(sunk,a);
		
		a.gridy = 1;
		a.gridx = 0;
		a.anchor = GridBagConstraints.LINE_START;
		score.add(cruiserLabel,a);
		
		a.gridy = 1;
		a.gridx = 1;
		a.anchor = GridBagConstraints.CENTER;
		score.add(cfloat,a);
		
		a.gridy = 1;
		a.gridx = 2;
		score.add(csunk,a);
		
		a.gridy = 2;
		a.gridx = 0;
		a.anchor = GridBagConstraints.LINE_START;
		score.add(destroyerLabel,a);
		
		a.gridy = 2;
		a.gridx = 1;
		a.anchor = GridBagConstraints.CENTER;
		score.add(dfloat,a);
		
		a.gridy = 2;
		a.gridx = 2;
		score.add(dsunk,a);
		
		a.gridy = 3;
		a.gridx = 0;
		a.anchor = GridBagConstraints.LINE_START;
		score.add(aircraftCarrierLabel,a);
		
		a.gridy = 3;
		a.gridx = 1;
		a.anchor = GridBagConstraints.CENTER;
		score.add(afloat,a);
		
		a.gridy = 3;
		a.gridx = 2;
		score.add(asunk,a);
		
		
		
		a.gridy = 4;
		a.gridx = 0;
		a.anchor = GridBagConstraints.LINE_START;
		score.add(clickNumLabel,a);
		
		a.anchor = GridBagConstraints.CENTER;
		a.gridwidth = 2;
		a.gridy = 4;
		a.gridx = 1;
		score.add(clickNum,a);
		
		JLabel statusLabel = new JLabel("Status: ");
		
		a.anchor = GridBagConstraints.LINE_START;
		a.gridwidth = 0;
		a.gridy = 5;
		a.gridx = 0;
		score.add(statusLabel, a);
		
		a.anchor = GridBagConstraints.CENTER;
		a.gridwidth = 2;
		hitMiss = new JLabel("");
		a.gridx = 1;
		score.add(hitMiss, a);
		
	}
	
	public void setShips(){
		int flag = 0;
		
//create first ship
		
		for(int i = 0; i < 2; i++){
			xy1[i] = rn.nextInt(6);
		}
		
		while(flag < 5)
		{
			while(flag < 1)
			{
				xy1[2] = xy1[0] + 1;
				xy1[5] = xy1[3] = xy1[1];
				xy1[4] = xy1[2] + 1;
				if(xy1[2] > 5 || xy1[4] > 5)
				{
						flag++;
				}
				else
				{
					flag = 5;
				}
			}
			
			while(flag < 2){
				xy1[2] = xy1[0] - 1;
				xy1[5] = xy1[3] = xy1[1];
				xy1[4] = xy1[2] - 1;
			if(xy1[2] < 0 || xy1[4] < 0)
			{
					flag++;
			}
			else
			{
				flag = 5;
			}
			}
		
		while(flag < 3){
			xy1[2] = xy1[4]= xy1[0];
			xy1[3] = xy1[1] + 1;
			xy1[5] = xy1[3] + 1;
		if(xy1[5] > 5 || xy1[3] > 5)
		{
				flag++;
		}
		else
		{
			flag = 5;
		}
		}
		
		while(flag < 4){
			xy1[2] = xy1[4]= xy1[0];
			xy1[3] = xy1[1] - 1;
			xy1[5] = xy1[3] - 1;
		if(xy1[5] < 0 || xy1[3] < 0)
		{
				flag++;
		}
		else
		{
			flag = 5;
		}
		}
		}
		
//create second ship
		flag = 0;
		int secondflag = 0;
	while(secondflag != 1){
		for(int i = 0; i < 2; i++){
			xy2[i] = rn.nextInt(6);
		}
		
		while(flag < 5)
		{
			while(flag < 1)
			{
				xy2[2] = xy2[0];
				xy2[3] = xy2[1] + 1;
				if(xy2[3] >= 6)
				{
						flag++;
				}
				else
				{
					flag = 5;
				}
			}
			
			while(flag < 2){
				xy2[2] = xy2[0];
				xy2[3] = xy2[1] - 1;
			if(xy2[3] <= -1)
			{
					flag++;
			}
			else
			{
				flag = 5;
			}
			}
			while(flag < 3)
			{
				xy2[2] = xy2[0] + 1;
				xy2[3] = xy2[1];
				if(xy2[2] >= 6)
				{
						flag++;
				}
				else
				{
					flag = 5;
				}
			}
			
			while(flag < 4){
				xy2[2] = xy2[0] - 1;
				xy2[3] = xy2[1];
			if(xy2[2] <= -1)
			{
					flag++;
			}
			else
			{
				flag = 5;
			}
			}
		}
		
		if(xy2[0] == xy1[0] && xy2[1] == xy1[1] ){
			secondflag = 0;
		}
		else if(xy2[0] == xy1[2] && xy2[1] == xy1[3]){
			secondflag = 0;
		}
		else if(xy2[0] == xy1[4] && xy2[1] == xy1[5]){
			secondflag = 0;
		}
		else if(xy2[2] == xy1[0] && xy2[3] == xy1[1] ){
			secondflag = 0;
		}
		else if( xy2[2] == xy1[2] && xy2[3] == xy1[3]){
			secondflag = 0;
		}
		else if(xy2[2] == xy1[4] && xy2[3] == xy1[5]){
			secondflag = 0;
		}
		else{
			secondflag++;
		}
		
//create third ship
		flag = 0;
		while(flag != 1){
		for(int i = 0; i < 2; i++){
			xy3[i] = rn.nextInt(6);
		}
		
		if(xy3[0] == xy1[0] && xy3[1] == xy1[1] ){
			flag = 0;
		}
		else if(xy3[0] == xy1[2] && xy3[1] == xy1[3]){
			flag = 0;
		}
		else if(xy3[0] == xy1[4] && xy3[1] == xy1[5]){
			flag = 0;
		}
		else if(xy3[0] == xy2[0] && xy3[1] == xy2[1] ){
			flag = 0;
		}
		else if( xy3[0] == xy2[2] && xy3[1] == xy2[3]){
			flag = 0;
		}
		else{
			flag++;
		}
		}
	}
		
		
	}
	
	public void checkHit(){
		if(xy1[0] == 0 && xy1[1] == 0){
			check[0] = 1;
			}
		else if(xy1[0] == 0 && xy1[1] == 1){
			check[1] = 1;
		}
		else if(xy1[0] == 0 && xy1[1] == 2){
			check[2] = 1;
		}
		else if(xy1[0] == 0 && xy1[1] == 3){
			check[3] = 1;
		}
		else if(xy1[0] == 0 && xy1[1] == 4){
			check[4] = 1;
		}
		else if(xy1[0] == 0 && xy1[1] == 5){
			check[5] = 1;
		}
		else if(xy1[0] == 1 && xy1[1] == 0){
			check[6] = 1;
		}
		else if(xy1[0] == 1 && xy1[1] == 1){
			check[7] = 1;
		}
		else if(xy1[0] == 1 && xy1[1] == 2){
			check[8] = 1;
		}
		else if(xy1[0] == 1 && xy1[1] == 3){
			check[9] = 1;
		}
		else if(xy1[0] == 1 && xy1[1] == 4){
			check[10] = 1;
		}
		else if(xy1[0] == 1 && xy1[1] == 5){
			check[11] = 1;
		}
		
		else if(xy1[0] == 2 && xy1[1] == 0){
			check[12] = 1;
		}
		else if(xy1[0] == 2 && xy1[1] == 1){
			check[13] = 1;
		}
		else if(xy1[0] == 2 && xy1[1] == 2){
			check[14] = 1;
		}
		else if(xy1[0] == 2 && xy1[1] == 3){
			check[15] = 1;
		}
		else if(xy1[0] == 2 && xy1[1] == 4){
			check[16] = 1;
		}
		else if(xy1[0] == 2 && xy1[1] == 5){
			check[17] = 1;
		}
		
		else if(xy1[0] == 3 && xy1[1] == 0){
			check[18] = 1;
		}
		else if(xy1[0] == 3 && xy1[1] == 1){
			check[19] = 1;
		}
		else if(xy1[0] == 3 && xy1[1] == 2){
			check[20] = 1;
		}
		else if(xy1[0] == 3 && xy1[1] == 3){
			check[21] = 1;
		}
		else if(xy1[0] == 3 && xy1[1] == 4){
			check[22] = 1;
		}
		else if(xy1[0] == 3 && xy1[1] == 5){
			check[23] = 1;
		}
		
		else if(xy1[0] == 4 && xy1[1] == 0){
			check[24] = 1;
		}
		else if(xy1[0] == 4 && xy1[1] == 1){
			check[25] = 1;
		}
		else if(xy1[0] == 4 && xy1[1] == 2){
			check[26] = 1;
		}
		else if(xy1[0] == 4 && xy1[1] == 3){
			check[27] = 1;
		}
		else if(xy1[0] == 4 && xy1[1] == 4){
			check[28] = 1;
		}
		else if(xy1[0] == 4 && xy1[1] == 5){
			check[29] = 1;
		}
		
		else if(xy1[0] == 5 && xy1[1] == 0){
			check[30] = 1;
		}
		else if(xy1[0] == 5 && xy1[1] == 1){
			check[31] = 1;
		}
		else if(xy1[0] == 5 && xy1[1] == 2){
			check[32] = 1;
		}
		else if(xy1[0] == 5 && xy1[1] == 3){
			check[33] = 1;
		}
		else if(xy1[0] == 5 && xy1[1] == 4){
			check[34] = 1;
		}
		else if(xy1[0] == 5 && xy1[1] == 5){
			check[35] = 1;
		}
//=============================================================
		if(xy1[2] == 0 && xy1[3] == 0){
			check[0] = 1;
		}
		else if(xy1[2] == 0 && xy1[3] == 1){
			check[1] = 1;
		}
		else if(xy1[2] == 0 && xy1[3] == 2){
			check[2] = 1;
		}
		else if(xy1[2] == 0 && xy1[3] == 3){
			check[3] = 1;
		}
		else if(xy1[2] == 0 && xy1[3] == 4){
			check[4] = 1;
		}
		else if(xy1[2] == 0 && xy1[3] == 5){
			check[5] = 1;
		}
		else if(xy1[2] == 1 && xy1[3] == 0){
			check[6] = 1;
		}
		else if(xy1[2] == 1 && xy1[3] == 1){
			check[7] = 1;
		}
		else if(xy1[2] == 1 && xy1[3] == 2){
			check[8] = 1;
		}
		else if(xy1[2] == 1 && xy1[3] == 3){
			check[9] = 1;
		}
		else if(xy1[2] == 1 && xy1[3] == 4){
			check[10] = 1;
		}
		else if(xy1[2] == 1 && xy1[3] == 5){
			check[11] = 1;
		}
		else if(xy1[2] == 2 && xy1[3] == 0){
			check[12] = 1;
		}
		else if(xy1[2] == 2 && xy1[3] == 1){
			check[13] = 1;
		}
		else if(xy1[2] == 2 && xy1[3] == 2){
			check[14] = 1;
		}
		else if(xy1[2] == 2 && xy1[3] == 3){
			check[15] = 1;
		}
		else if(xy1[2] == 2 && xy1[3] == 4){
			check[16] = 1;
		}
		else if(xy1[2] == 2 && xy1[3] == 5){
			check[17] = 1;
		}
		else if(xy1[2] == 3 && xy1[3] == 0){
			check[18] = 1;
		}
		else if(xy1[2] == 3 && xy1[3] == 1){
			check[19] = 1;
		}
		else if(xy1[2] == 3 && xy1[3] == 2){
			check[20] = 1;
		}
		else if(xy1[2] == 3 && xy1[3] == 3){
			check[21] = 1;
		}
		else if(xy1[2] == 3 && xy1[3] == 4){
			check[22] = 1;
		}
		else if(xy1[2] == 3 && xy1[3] == 5){
			check[23] = 1;
		}
		else if(xy1[2] == 4 && xy1[3] == 0){
			check[24] = 1;
		}
		else if(xy1[2] == 4 && xy1[3] == 1){
			check[25] = 1;
		}
		else if(xy1[2] == 4 && xy1[3] == 2){
			check[26] = 1;
		}
		else if(xy1[2] == 4 && xy1[3] == 3){
			check[27] = 1;
		}
		else if(xy1[2] == 4 && xy1[3] == 4){
			check[28] = 1;
		}
		else if(xy1[2] == 4 && xy1[3] == 5){
			check[29] = 1;
		}
		else if(xy1[2] == 5 && xy1[3] == 0){
			check[30] = 1;
		}
		else if(xy1[2] == 5 && xy1[3] == 1){
			check[31] = 1;
		}
		else if(xy1[2] == 5 && xy1[3] == 2){
			check[32] = 1;
		}
		else if(xy1[2] == 5 && xy1[3] == 3){
			check[33] = 1;
		}
		else if(xy1[2] == 5 && xy1[3] == 4){
			check[34] = 1;
		}
		else if(xy1[2] == 5 && xy1[3] == 5){
			check[35] = 1;
		}
//==================================================================
		if(xy1[4] == 0 && xy1[5] == 0){
			check[0] = 1;
		}
		else if(xy1[4] == 0 && xy1[5] == 1){
			check[1] = 1;
		}
		else if(xy1[4] == 0 && xy1[5] == 2){
			check[2] = 1;
		}
		else if(xy1[4] == 0 && xy1[5] == 3){
			check[3] = 1;
		}
		else if(xy1[4] == 0 && xy1[5] == 4){
			check[4] = 1;
		}
		else if(xy1[4] == 0 && xy1[5] == 5){
			check[5] = 1;
		}
		else if(xy1[4] == 1 && xy1[5] == 0){
			check[6] = 1;
		}
		else if(xy1[4] == 1 && xy1[5] == 1){
			check[7] = 1;
		}
		else if(xy1[4] == 1 && xy1[5] == 2){
			check[8] = 1;
		}
		else if(xy1[4] == 1 && xy1[5] == 3){
			check[9] = 1;
		}
		else if(xy1[4] == 1 && xy1[5] == 4){
			check[10] = 1;
		}
		else if(xy1[4] == 1 && xy1[5] == 5){
			check[11] = 1;
		}
		else if(xy1[4] == 2 && xy1[5] == 0){
			check[12] = 1;
		}
		else if(xy1[4] == 2 && xy1[5] == 1){
			check[13] = 1;
		}
		else if(xy1[4] == 2 && xy1[5] == 2){
			check[14] = 1;
		}
		else if(xy1[4] == 2 && xy1[5] == 3){
			check[15] = 1;
		}
		else if(xy1[4] == 2 && xy1[5] == 4){
			check[16] = 1;
		}
		else if(xy1[4] == 2 && xy1[5] == 5){
			check[17] = 1;
		}
		else if(xy1[4] == 3 && xy1[5] == 0){
			check[18] = 1;
		}
		else if(xy1[4] == 3 && xy1[5] == 1){
			check[19] = 1;
		}
		else if(xy1[4] == 3 && xy1[5] == 2){
			check[20] = 1;
		}
		else if(xy1[4] == 3 && xy1[5] == 3){
			check[21] = 1;
		}
		else if(xy1[4] == 3 && xy1[5] == 4){
			check[22] = 1;
		}
		else if(xy1[4] == 3 && xy1[5] == 5){
			check[23] = 1;
		}
		else if(xy1[4] == 4 && xy1[5] == 0){
			check[24] = 1;
		}
		else if(xy1[4] == 4 && xy1[5] == 1){
			check[25] = 1;
		}
		else if(xy1[4] == 4 && xy1[5] == 2){
			check[26] = 1;
		}
		else if(xy1[4] == 4 && xy1[5] == 3){
			check[27] = 1;
		}
		else if(xy1[4] == 4 && xy1[5] == 4){
			check[28] = 1;
		}
		else if(xy1[4] == 4 && xy1[5] == 5){
			check[29] = 1;
		}
		else if(xy1[4] == 5 && xy1[5] == 0){
			check[30] = 1;
		}
		else if(xy1[4] == 5 && xy1[5] == 1){
			check[31] = 1;
		}
		else if(xy1[4] == 5 && xy1[5] == 2){
			check[32] = 1;
		}
		else if(xy1[4] == 5 && xy1[5] == 3){
			check[33] = 1;
		}
		else if(xy1[4] == 5 && xy1[5] == 4){
			check[34] = 1;
		}
		else if(xy1[4] == 5 && xy1[5] == 5){
			check[35] = 1;
		}
//====================================================================
		if(xy2[0] == 0 && xy2[1] == 0){
			check1[0] = 1;
		}
		else if(xy2[0] == 0 && xy2[1] == 1){
			check1[1] = 1;
		}
		else if(xy2[0] == 0 && xy2[1] == 2){
			check1[2] = 1;
		}
		else if(xy2[0] == 0 && xy2[1] == 3){
			check1[3] = 1;
		}
		else if(xy2[0] == 0 && xy2[1] == 4){
			check1[4] = 1;
		}
		else if(xy2[0] == 0 && xy2[1] == 5){
			check1[5] = 1;
		}
		else if(xy2[0] == 1 && xy2[1] == 0){
			check1[6] = 1;
		}
		else if(xy2[0] == 1 && xy2[1] == 1){
			check1[7] = 1;
		}
		else if(xy2[0] == 1 && xy2[1] == 2){
			check1[8] = 1;
		}
		else if(xy2[0] == 1 && xy2[1] == 3){
			check1[9] = 1;
		}
		else if(xy2[0] == 1 && xy2[1] == 4){
			check1[10] = 1;
		}
		else if(xy2[0] == 1 && xy2[1] == 5){
			check1[11] = 1;
		}
		else if(xy2[0] == 2 && xy2[1] == 0){
			check1[12] = 1;
		}
		else if(xy2[0] == 2 && xy2[1] == 1){
			check1[13] = 1;
		}
		else if(xy2[0] == 2 && xy2[1] == 2){
			check1[14] = 1;
		}
		else if(xy2[0] == 2 && xy2[1] == 3){
			check1[15] = 1;
		}
		else if(xy2[0] == 2 && xy2[1] == 4){
			check1[16] = 1;
		}
		else if(xy2[0] == 2 && xy2[1] == 5){
			check1[17] = 1;
		}
		else if(xy2[0] == 3 && xy2[1] == 0){
			check1[18] = 1;
		}
		else if(xy2[0] == 3 && xy2[1] == 1){
			check1[19] = 1;
		}
		else if(xy2[0] == 3 && xy2[1] == 2){
			check1[20] = 1;
		}
		else if(xy2[0] == 3 && xy2[1] == 3){
			check1[21] = 1;
		}
		else if(xy2[0] == 3 && xy2[1] == 4){
			check1[22] = 1;
		}
		else if(xy2[0] == 3 && xy2[1] == 5){
			check1[23] = 1;
		}
		else if(xy2[0] == 4 && xy2[1] == 0){
			check1[24] = 1;
		}
		else if(xy2[0] == 4 && xy2[1] == 1){
			check1[25] = 1;
		}
		else if(xy2[0] == 4 && xy2[1] == 2){
			check1[26] = 1;
		}
		else if(xy2[0] == 4 && xy2[1] == 3){
			check1[27] = 1;
		}
		else if(xy2[0] == 4 && xy2[1] == 4){
			check1[28] = 1;
		}
		else if(xy2[0] == 4 && xy2[1] == 5){
			check1[29] = 1;
		}
		else if(xy2[0] == 5 && xy2[1] == 0){
			check1[30] = 1;
		}
		else if(xy2[0] == 5 && xy2[1] == 1){
			check1[31] = 1;
		}
		else if(xy2[0] == 5 && xy2[1] == 2){
			check1[32] = 1;
		}
		else if(xy2[0] == 5 && xy2[1] == 3){
			check1[33] = 1;
		}
		else if(xy2[0] == 5 && xy2[1] == 4){
			check1[34] = 1;
		}
		else if(xy2[0] == 5 && xy2[1] == 5){
			check1[35] = 1;
		}
		
//=====================================================================
		if(xy2[2] == 0 && xy2[3] == 0){
			check1[0] = 1;
		}
		else if(xy2[2] == 0 && xy2[3] == 1){
			check1[1] = 1;
		}
		else if(xy2[2] == 0 && xy2[3] == 2){
			check1[2] = 1;
		}
		else if(xy2[2] == 0 && xy2[3] == 3){
			check1[3] = 1;
		}
		else if(xy2[2] == 0 && xy2[3] == 4){
			check1[4] = 1;
		}
		else if(xy2[2] == 0 && xy2[3] == 5){
			check1[5] = 1;
		}
		else if(xy2[2] == 1 && xy2[3] == 0){
			check1[6] = 1;
		}
		else if(xy2[2] == 1 && xy2[3] == 1){
			check1[7] = 1;
		}
		else if(xy2[2] == 1 && xy2[3] == 2){
			check1[8] = 1;
		}
		else if(xy2[2] == 1 && xy2[3] == 3){
			check1[9] = 1;
		}
		else if(xy2[2] == 1 && xy2[3] == 4){
			check1[10] = 1;
		}
		else if(xy2[2] == 1 && xy2[3] == 5){
			check1[11] = 1;
		}
		else if(xy2[2] == 2 && xy2[3] == 0){
			check1[12] = 1;
		}
		else if(xy2[2] == 2 && xy2[3] == 1){
			check1[13] = 1;
		}
		else if(xy2[2] == 2 && xy2[3] == 2){
			check1[14] = 1;
		}
		else if(xy2[2] == 2 && xy2[3] == 3){
			check1[15] = 1;
		}
		else if(xy2[2] == 2 && xy2[3] == 4){
			check1[16] = 1;
		}
		else if(xy2[2] == 2 && xy2[3] == 5){
			check1[17] = 1;
		}
		else if(xy2[2] == 3 && xy2[3] == 0){
			check1[18] = 1;
		}
		else if(xy2[2] == 3 && xy2[3] == 1){
			check1[19] = 1;
		}
		else if(xy2[2] == 3 && xy2[3] == 2){
			check1[20] = 1;
		}
		else if(xy2[2] == 3 && xy2[3] == 3){
			check1[21] = 1;
		}
		else if(xy2[2] == 3 && xy2[3] == 4){
			check1[22] = 1;
		}
		else if(xy2[2] == 3 && xy2[3] == 5){
			check1[23] = 1;
		}
		else if(xy2[2] == 4 && xy2[3] == 0){
			check1[24] = 1;
		}
		else if(xy2[2] == 4 && xy2[3] == 1){
			check1[25] = 1;
		}
		else if(xy2[2] == 4 && xy2[3] == 2){
			check1[26] = 1;
		}
		else if(xy2[2] == 4 && xy2[3] == 3){
			check1[27] = 1;
		}
		else if(xy2[2] == 4 && xy2[3] == 4){
			check1[28] = 1;
		}
		else if(xy2[2] == 4 && xy2[3] == 5){
			check1[29] = 1;
		}
		else if(xy2[2] == 5 && xy2[3] == 0){
			check1[30] = 1;
		}
		else if(xy2[2] == 5 && xy2[3] == 1){
			check1[31] = 1;
		}
		else if(xy2[2] == 5 && xy2[3] == 2){
			check1[32] = 1;
		}
		else if(xy2[2] == 5 && xy2[3] == 3){
			check1[33] = 1;
		}
		else if(xy2[2] == 5 && xy2[3] == 4){
			check1[34] = 1;
		}
		else if(xy2[2] == 5 && xy2[3] == 5){
			check1[35] = 1;
		}
		
//===================================================
		
		if(xy3[0] == 0 && xy3[1] == 0){
			check2[0] = 1;
		}
		else if(xy3[0] == 0 && xy3[1] == 1){
			check2[1] = 1;
		}
		else if(xy3[0] == 0 && xy3[1] == 2){
			check2[2] = 1;
		}
		else if(xy3[0] == 0 && xy3[1] == 3){
			check2[3] = 1;
		}
		else if(xy3[0] == 0 && xy3[1] == 4){
			check2[4] = 1;
		}
		else if(xy3[0] == 0 && xy3[1] == 5){
			check2[5] = 1;
		}
		else if(xy3[0] == 1 && xy3[1] == 0){
			check2[6] = 1;
		}
		else if(xy3[0] == 1 && xy3[1] == 1){
			check2[7] = 1;
		}
		else if(xy3[0] == 1 && xy3[1] == 2){
			check2[8] = 1;
		}
		else if(xy3[0] == 1 && xy3[1] == 3){
			check2[9] = 1;
		}
		else if(xy3[0] == 1 && xy3[1] == 4){
			check2[10] = 1;
		}
		else if(xy3[0] == 1 && xy3[1] == 5){
			check2[11] = 1;
		}
		else if(xy3[0] == 2 && xy3[1] == 0){
			check2[12] = 1;
		}
		else if(xy3[0] == 2 && xy3[1] == 1){
			check2[13] = 1;
		}
		else if(xy3[0] == 2 && xy3[1] == 2){
			check2[14] = 1;
		}
		else if(xy3[0] == 2 && xy3[1] == 3){
			check2[15] = 1;
		}
		else if(xy3[0] == 2 && xy3[1] == 4){
			check2[16] = 1;
		}
		else if(xy3[0] == 2 && xy3[1] == 5){
			check2[17] = 1;
		}
		else if(xy3[0] == 3 && xy3[1] == 0){
			check2[18] = 1;
		}
		else if(xy3[0] == 3 && xy3[1] == 1){
			check2[19] = 1;
		}
		else if(xy3[0] == 3 && xy3[1] == 2){
			check2[20] = 1;
		}
		else if(xy3[0] == 3 && xy3[1] == 3){
			check2[21] = 1;
		}
		else if(xy3[0] == 3 && xy3[1] == 4){
			check2[22] = 1;
		}
		else if(xy3[0] == 3 && xy3[1] == 5){
			check2[23] = 1;
		}
		else if(xy3[0] == 4 && xy3[1] == 0){
			check2[24] = 1;
		}
		else if(xy3[0] == 4 && xy3[1] == 1){
			check2[25] = 1;
		}
		else if(xy3[0] == 4 && xy3[1] == 2){
			check2[26] = 1;
		}
		else if(xy3[0] == 4 && xy3[1] == 3){
			check2[27] = 1;
		}
		else if(xy3[0] == 4 && xy3[1] == 4){
			check2[28] = 1;
		}
		else if(xy3[0] == 4 && xy3[1] == 5){
			check2[29] = 1;
		}
		else if(xy3[0] == 5 && xy3[1] == 0){
			check2[30] = 1;
		}
		else if(xy3[0] == 5 && xy3[1] == 1){
			check2[31] = 1;
		}
		else if(xy3[0] == 5 && xy3[1] == 2){
			check2[32] = 1;
		}
		else if(xy3[0] == 5 && xy3[1] == 3){
			check2[33] = 1;
		}
		else if(xy3[0] == 5 && xy3[1] == 4){
			check2[34] = 1;
		}
		else if(xy3[0] == 5 && xy3[1] == 5){
			check2[35] = 1;
		}
	}
	
	public void addPaneClicks(){
		a1.addMouseListener(new clicked());
		b1.addMouseListener(new clicked());
		c1.addMouseListener(new clicked());
		d1.addMouseListener(new clicked());
		e1.addMouseListener(new clicked());
		f1.addMouseListener(new clicked());
		
		a2.addMouseListener(new clicked());
		b2.addMouseListener(new clicked());
		c2.addMouseListener(new clicked());
		d2.addMouseListener(new clicked());
		e2.addMouseListener(new clicked());
		f2.addMouseListener(new clicked());
		
		a3.addMouseListener(new clicked());
		b3.addMouseListener(new clicked());
		c3.addMouseListener(new clicked());
		d3.addMouseListener(new clicked());
		e3.addMouseListener(new clicked());
		f3.addMouseListener(new clicked());
		
		a4.addMouseListener(new clicked());
		b4.addMouseListener(new clicked());
		c4.addMouseListener(new clicked());
		d4.addMouseListener(new clicked());
		e4.addMouseListener(new clicked());
		f4.addMouseListener(new clicked());
		
		a5.addMouseListener(new clicked());
		b5.addMouseListener(new clicked());
		c5.addMouseListener(new clicked());
		d5.addMouseListener(new clicked());
		e5.addMouseListener(new clicked());
		f5.addMouseListener(new clicked());
		
		a6.addMouseListener(new clicked());
		b6.addMouseListener(new clicked());
		c6.addMouseListener(new clicked());
		d6.addMouseListener(new clicked());
		e6.addMouseListener(new clicked());
		f6.addMouseListener(new clicked());
		
	}
	
	public void cheat(){
		System.out.println((xy1[0] + 1) +","+ (xy1[1] + 1));
		System.out.println((xy1[2]+1) +","+ (xy1[3]+1));
		System.out.println((xy1[4]+1) +","+ (xy1[5]+1));
		
		System.out.println();
		
		System.out.println((xy2[0]+1) +","+ (xy2[1]+1));
		System.out.println((xy2[2]+1) +","+ (xy2[3]+1));
		
		System.out.println();
		
		System.out.println((xy3[0]+1) +","+ (xy3[1]+1));
	}
	
	public void buildBattlefield(){
		a1 = new JPanel();
		b1 = new JPanel();
		c1 = new JPanel();
		d1 = new JPanel();
		e1 = new JPanel();
		f1 = new JPanel();
		
		battlefield.add(a1);
		battlefield.add(b1);
		battlefield.add(c1);
		battlefield.add(d1);
		battlefield.add(e1);
		battlefield.add(f1);
		
		a2 = new JPanel();
		b2 = new JPanel();
		c2 = new JPanel();
		d2 = new JPanel();
		e2 = new JPanel();
		f2 = new JPanel();
		
		battlefield.add(a2);
		battlefield.add(b2);
		battlefield.add(c2);
		battlefield.add(d2);
		battlefield.add(e2);
		battlefield.add(f2);
		
		a3 = new JPanel();
		b3 = new JPanel();
		c3 = new JPanel();
		d3 = new JPanel();
		e3 = new JPanel();
		f3 = new JPanel();
		
		battlefield.add(a3);
		battlefield.add(b3);
		battlefield.add(c3);
		battlefield.add(d3);
		battlefield.add(e3);
		battlefield.add(f3);
		
		a4 = new JPanel();
		b4 = new JPanel();
		c4 = new JPanel();
		d4 = new JPanel();
		e4 = new JPanel();
		f4 = new JPanel();
		
		battlefield.add(a4);
		battlefield.add(b4);
		battlefield.add(c4);
		battlefield.add(d4);
		battlefield.add(e4);
		battlefield.add(f4);
		
		a5 = new JPanel();
		b5 = new JPanel();
		c5 = new JPanel();
		d5 = new JPanel();
		e5 = new JPanel();
		f5 = new JPanel();
		
		battlefield.add(a5);
		battlefield.add(b5);
		battlefield.add(c5);
		battlefield.add(d5);
		battlefield.add(e5);
		battlefield.add(f5);
		
		a6 = new JPanel();
		b6 = new JPanel();
		c6 = new JPanel();
		d6 = new JPanel();
		e6 = new JPanel();
		f6 = new JPanel();
		
		battlefield.add(a6);
		battlefield.add(b6);
		battlefield.add(c6);
		battlefield.add(d6);
		battlefield.add(e6);
		battlefield.add(f6);
		
		a1.setBorder(border);
		b1.setBorder(border);
		c1.setBorder(border);
		d1.setBorder(border);
		e1.setBorder(border);
		f1.setBorder(border);
		
		a2.setBorder(border);
		b2.setBorder(border);
		c2.setBorder(border);
		d2.setBorder(border);
		e2.setBorder(border);
		f2.setBorder(border);
		
		a3.setBorder(border);
		b3.setBorder(border);
		c3.setBorder(border);
		d3.setBorder(border);
		e3.setBorder(border);
		f3.setBorder(border);
		
		a4.setBorder(border);
		b4.setBorder(border);
		c4.setBorder(border);
		d4.setBorder(border);
		e4.setBorder(border);
		f4.setBorder(border);
		
		a5.setBorder(border);
		b5.setBorder(border);
		c5.setBorder(border);
		d5.setBorder(border);
		e5.setBorder(border);
		f5.setBorder(border);
		
		a6.setBorder(border);
		b6.setBorder(border);
		c6.setBorder(border);
		d6.setBorder(border);
		e6.setBorder(border);
		f6.setBorder(border);
	}
	
	public class clicked implements MouseListener
	{
		public void mouseClicked(MouseEvent e) 
		{
			
			if(e.getSource() == a1 && check[0] == 1 && acheck[0] != 1 && aircraftCarrier <= 3){
				a1.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[0] = 1;
				missCheck[0] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a2 && check[1] == 1 && acheck[1] != 1 && aircraftCarrier <= 3){
				a2.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[1] = 1;
				missCheck[1] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a3 && check[2] == 1 && acheck[2] != 1 && aircraftCarrier <= 3){
				a3.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[2] = 1;
				missCheck[2] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a4 && check[3] == 1 && acheck[3] != 1 && aircraftCarrier <= 3){
				a4.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[3] = 1;
				missCheck[3] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a5 && check[4] == 1 && acheck[4] != 1 && aircraftCarrier <= 3){
				a5.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[4] = 1;
				missCheck[4] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a6 && check[5] == 1 && acheck[5] != 1 && aircraftCarrier <= 3){
				a6.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[5] = 1;
				missCheck[5] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b1 && check[6] == 1 && acheck[6] != 1 && aircraftCarrier <= 3){
				b1.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[6] = 1;
				missCheck[6] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b2 && check[7] == 1 && acheck[7] != 1 && aircraftCarrier <= 3){
				b2.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[7] = 1;
				missCheck[7] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b3 && check[8] == 1 && acheck[8] != 1 && aircraftCarrier <= 3){
				b3.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[8] = 1;
				missCheck[8] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b4 && check[9] == 1 && acheck[9] != 1 && aircraftCarrier <= 3){
				b4.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[9] = 1;
				missCheck[9] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b5 && check[10] == 1 && acheck[10] != 1 && aircraftCarrier <= 3){
				b5.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[10] = 1;
				missCheck[10] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b6 && check[11] == 1 && acheck[11] != 1 && aircraftCarrier <= 3){
				b6.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[11] = 1;
				missCheck[11] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c1 && check[12] == 1 && acheck[12] != 1 && aircraftCarrier <= 3){
				c1.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[12] = 1;
				missCheck[12] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c2 && check[13] == 1 && acheck[13] != 1 && aircraftCarrier <= 3){
				c2.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[13] = 1;
				missCheck[13] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c3  && check[14] == 1 && acheck[14] != 1 && aircraftCarrier <= 3){
				c3.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[14] = 1;
				missCheck[14] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c4 && check[15] == 1 && acheck[15] != 1 && aircraftCarrier <= 3){
				c4.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[15] = 1;
				missCheck[15] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c5 && check[16] == 1 && acheck[16] != 1 && aircraftCarrier <= 3){
				c5.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[16] = 1;
				missCheck[16] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c6 && check[17] == 1 && acheck[17] != 1 && aircraftCarrier <= 3){
				c6.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[17] = 1;
				missCheck[17] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			
			else if(e.getSource() == d1 && check[18] == 1 && acheck[18] != 1 && aircraftCarrier <= 3){
				d1.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[18] = 1;
				missCheck[18] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d2 && check[19] == 1 && acheck[19] != 1 && aircraftCarrier <= 3){
				d2.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[19] = 1;
				missCheck[19] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d3 && check[20] == 1 && acheck[20] != 1 && aircraftCarrier <= 3){
				d3.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[20] = 1;
				missCheck[20] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d4 && check[21] == 1 && acheck[21] != 1 && aircraftCarrier <= 3){
				d4.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[21] = 1;
				missCheck[21] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d5 && check[22] == 1 && acheck[22] != 1 && aircraftCarrier <= 3){
				d5.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[22] = 1;
				missCheck[22] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d6 && check[23] == 1 && acheck[23] != 1 && aircraftCarrier <= 3){
				d6.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[23] = 1;
				missCheck[23] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e1 && check[24] == 1 && acheck[24] != 1 && aircraftCarrier <= 3){
				e1.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[24] = 1;
				missCheck[24] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e2 && check[25] == 1 && acheck[25] != 1 && aircraftCarrier <= 3){
				e2.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[25] = 1;
				missCheck[25] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e3 && check[26] == 1 && acheck[26] != 1 && aircraftCarrier <= 3){
				e3.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[26] = 1;
				missCheck[26] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e4 && check[27] == 1 && acheck[27] != 1 && aircraftCarrier <= 3){
				e4.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[27] = 1;
				missCheck[27] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e5 && check[28] == 1 && acheck[28] != 1 && aircraftCarrier <= 3){
				e5.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[28] = 1;
				missCheck[28] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e6 && check[29] == 1 && acheck[29] != 1 && aircraftCarrier <= 3){
				e6.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[29] = 1;
				missCheck[29] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
				
			else if(e.getSource() == f1 && check[30] == 1 && acheck[30] != 1 && aircraftCarrier <= 3){
				f1.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[30] = 1;
				missCheck[30] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f2 && check[31] == 1 && acheck[31] != 1 && aircraftCarrier <= 3){
				f2.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[31] = 1;
				missCheck[31] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f3 && check[32] == 1 && acheck[32] != 1 && aircraftCarrier <= 3){
				f3.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[32] = 1;
				missCheck[32] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f4 && check[33] == 1 && acheck[33] != 1 && aircraftCarrier <= 3){
				f4.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[33] = 1;
				missCheck[33] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f5  && check[34] == 1 && acheck[34] != 1 && aircraftCarrier <= 3){
				f5.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[34] = 1;
				missCheck[34] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f6 && check[35] == 1 && acheck[35] != 1 && aircraftCarrier <= 3){
				f6.setBackground(Color.RED);
				aircraftCarrier++;
				acheck[35] = 1;
				missCheck[35] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			
//======================================================================
			
			else if(e.getSource() == a1 && check1[0] == 1 && dcheck[0] != 1 && destroyer <= 2){
				a1.setBackground(Color.RED);
				destroyer++;
				dcheck[0] = 1;
				missCheck[0] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a2 && check1[1] == 1 && dcheck[1] != 1 && destroyer <= 2){
				a2.setBackground(Color.RED);
				destroyer++;
				dcheck[1] = 1;
				missCheck[1] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a3 && check1[2] == 1 && dcheck[2] != 1 && destroyer <= 2){
				a3.setBackground(Color.RED);
				destroyer++;
				dcheck[2] = 1;
				missCheck[2] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a4 && check1[3] == 1 && dcheck[3] != 1 && destroyer <= 2){
				a4.setBackground(Color.RED);
				destroyer++;
				dcheck[3] = 1;
				missCheck[3] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a5 && check1[4] == 1 && dcheck[4] != 1 && destroyer <= 2){
				a5.setBackground(Color.RED);
				destroyer++;
				dcheck[4] = 1;
				missCheck[4] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a6 && check1[5] == 1 && dcheck[5] != 1 && destroyer <= 2){
				a6.setBackground(Color.RED);
				destroyer++;
				dcheck[5] = 1;
				missCheck[5] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b1 && check1[6] == 1 && dcheck[6] != 1 && destroyer <= 2){
				b1.setBackground(Color.RED);
				destroyer++;
				dcheck[6] = 1;
				missCheck[6] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b2 && check1[7] == 1 && dcheck[7] != 1 && destroyer <= 2){
				b2.setBackground(Color.RED);
				destroyer++;
				dcheck[7] = 1;
				missCheck[7] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b3 && check1[8] == 1 && dcheck[8] != 1 && destroyer <= 2){
				b3.setBackground(Color.RED);
				destroyer++;
				dcheck[8] = 1;
				missCheck[8] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b4 && check1[9] == 1 && dcheck[9] != 1 && destroyer <= 2){
				b4.setBackground(Color.RED);
				destroyer++;
				dcheck[9] = 1;
				missCheck[9] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b5 && check1[10] == 1 && dcheck[10] != 1 && destroyer <= 2){
				b5.setBackground(Color.RED);
				destroyer++;
				dcheck[10] = 1;
				missCheck[10] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b6 && check1[11] == 1 && dcheck[11] != 1 && destroyer <= 2){
				b6.setBackground(Color.RED);
				destroyer++;
				dcheck[11] = 1;
				missCheck[11] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c1 && check1[12] == 1 && dcheck[12] != 1 && destroyer <= 2){
				c1.setBackground(Color.RED);
				destroyer++;
				dcheck[12] = 1;
				missCheck[12] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c2 && check1[13] == 1 && dcheck[13] != 1 && destroyer <= 2){
				c2.setBackground(Color.RED);
				destroyer++;
				dcheck[13] = 1;
				missCheck[13] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c3  && check1[14] == 1 && dcheck[14] != 1 && destroyer <= 2){
				c3.setBackground(Color.RED);
				destroyer++;
				dcheck[14] = 1;
				missCheck[14] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c4 && check1[15] == 1 && dcheck[15] != 1 && destroyer <= 2){
				c4.setBackground(Color.RED);
				destroyer++;
				dcheck[15] = 1;
				missCheck[15] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c5 && check1[16] == 1 && dcheck[16] != 1 && destroyer <= 2){
				c5.setBackground(Color.RED);
				destroyer++;
				dcheck[16] = 1;
				missCheck[16] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c6 && check1[17] == 1 && dcheck[17] != 1 && destroyer <= 2){
				c6.setBackground(Color.RED);
				destroyer++;
				dcheck[17] = 1;
				missCheck[17] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			
			else if(e.getSource() == d1 && check1[18] == 1 && dcheck[18] != 1 && destroyer <= 2){
				d1.setBackground(Color.RED);
				destroyer++;
				dcheck[18] = 1;
				missCheck[18] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d2 && check1[19] == 1 && dcheck[19] != 1 && destroyer <= 2){
				d2.setBackground(Color.RED);
				destroyer++;
				dcheck[19] = 1;
				missCheck[19] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d3 && check1[20] == 1 && dcheck[20] != 1 && destroyer <= 2){
				d3.setBackground(Color.RED);
				destroyer++;
				dcheck[20] = 1;
				missCheck[20] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d4 && check1[21] == 1 && dcheck[21] != 1 && destroyer <= 2){
				d4.setBackground(Color.RED);
				destroyer++;
				dcheck[21] = 1;
				missCheck[21] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d5 && check1[22] == 1 && dcheck[22] != 1 && destroyer <= 2){
				d5.setBackground(Color.RED);
				destroyer++;
				dcheck[22] = 1;
				missCheck[22] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d6 && check1[23] == 1 && dcheck[23] != 1 && destroyer <= 2){
				d6.setBackground(Color.RED);
				destroyer++;
				dcheck[23] = 1;
				missCheck[23] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e1 && check1[24] == 1 && dcheck[24] != 1 && destroyer <= 2){
				e1.setBackground(Color.RED);
				destroyer++;
				dcheck[24] = 1;
				missCheck[24] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e2 && check1[25] == 1 && dcheck[25] != 1 && destroyer <= 2){
				e2.setBackground(Color.RED);
				destroyer++;
				dcheck[25] = 1;
				missCheck[25] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e3 && check1[26] == 1 && dcheck[26] != 1 && destroyer <= 2){
				e3.setBackground(Color.RED);
				destroyer++;
				dcheck[26] = 1;
				missCheck[26] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e4 && check1[27] == 1 && dcheck[27] != 1 && destroyer <= 2){
				e4.setBackground(Color.RED);
				destroyer++;
				dcheck[27] = 1;
				missCheck[27] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e5 && check1[28] == 1 && dcheck[28] != 1 && destroyer <= 2){
				e5.setBackground(Color.RED);
				destroyer++;
				dcheck[28] = 1;
				missCheck[28] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e6 && check1[29] == 1 && dcheck[29] != 1 && destroyer <= 2){
				e6.setBackground(Color.RED);
				destroyer++;
				dcheck[29] = 1;
				missCheck[29] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
				
			else if(e.getSource() == f1 && check1[30] == 1 && dcheck[30] != 1 && destroyer <= 2){
				f1.setBackground(Color.RED);
				destroyer++;
				dcheck[30] = 1;
				missCheck[30] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f2 && check1[31] == 1 && dcheck[31] != 1 && destroyer <= 2){
				f2.setBackground(Color.RED);
				destroyer++;
				dcheck[31] = 1;
				missCheck[31] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f3 && check1[32] == 1 && dcheck[32] != 1 && destroyer <= 2){
				f3.setBackground(Color.RED);
				destroyer++;
				dcheck[32] = 1;
				missCheck[32] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f4 && check1[33] == 1 && dcheck[33] != 1 && destroyer <= 2){
				f4.setBackground(Color.RED);
				destroyer++;
				dcheck[33] = 1;
				missCheck[33] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f5  && check1[34] == 1 && dcheck[34] != 1 && destroyer <= 2){
				f5.setBackground(Color.RED);
				destroyer++;
				dcheck[34] = 1;
				missCheck[34] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f6 && check1[35] == 1 && dcheck[35] != 1 && destroyer <= 2){
				f6.setBackground(Color.RED);
				destroyer++;
				dcheck[35] = 1;
				missCheck[35] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
//================================================================
			
			else if(e.getSource() == a1 && check2[0] == 1 && ccheck[0] != 1 && cruiser <= 1){
				a1.setBackground(Color.RED);
				cruiser++;
				ccheck[0] = 1;
				missCheck[0] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a2 && check2[1] == 1 && ccheck[1] != 1 && cruiser <= 1){
				a2.setBackground(Color.RED);
				cruiser++;
				ccheck[1] = 1;
				missCheck[1] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a3 && check2[2] == 1 && ccheck[2] != 1 && cruiser <= 1){
				a3.setBackground(Color.RED);
				cruiser++;
				ccheck[2] = 1;
				missCheck[2] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a4 && check2[3] == 1 && ccheck[3] != 1 && cruiser <= 1){
				a4.setBackground(Color.RED);
				cruiser++;
				ccheck[3] = 1;
				missCheck[3] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a5 && check2[4] == 1 && ccheck[4] != 1 && cruiser <= 1){
				a5.setBackground(Color.RED);
				cruiser++;
				ccheck[4] = 1;
				missCheck[4] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == a6 && check2[5] == 1 && ccheck[5] != 1 && cruiser <= 1){
				a6.setBackground(Color.RED);
				cruiser++;
				ccheck[5] = 1;
				missCheck[5] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b1 && check2[6] == 1 && ccheck[6] != 1 && cruiser <= 1){
				b1.setBackground(Color.RED);
				cruiser++;
				ccheck[6] = 1;
				missCheck[6] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b2 && check2[7] == 1 && ccheck[7] != 1 && cruiser <= 1){
				b2.setBackground(Color.RED);
				cruiser++;
				ccheck[7] = 1;
				missCheck[7] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b3 && check2[8] == 1 && ccheck[8] != 1 && cruiser <= 1){
				b3.setBackground(Color.RED);
				cruiser++;
				ccheck[8] = 1;
				missCheck[8] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b4 && check2[9] == 1 && ccheck[9] != 1 && cruiser <= 1){
				b4.setBackground(Color.RED);
				cruiser++;
				ccheck[9] = 1;
				missCheck[9] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b5 && check2[10] == 1 && ccheck[10] != 1 && cruiser <= 1){
				b5.setBackground(Color.RED);
				cruiser++;
				ccheck[10] = 1;
				missCheck[10] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == b6 && check2[11] == 1 && ccheck[11] != 1 && cruiser <= 1){
				b6.setBackground(Color.RED);
				cruiser++;
				ccheck[11] = 1;
				missCheck[11] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c1 && check2[12] == 1 && ccheck[12] != 1 && cruiser <= 1){
				c1.setBackground(Color.RED);
				cruiser++;
				ccheck[12] = 1;
				missCheck[12] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c2 && check2[13] == 1 && ccheck[13] != 1 && cruiser <= 1){
				c2.setBackground(Color.RED);
				cruiser++;
				ccheck[13] = 1;
				missCheck[13] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c3  && check2[14] == 1 && ccheck[14] != 1 && cruiser <= 1){
				c3.setBackground(Color.RED);
				cruiser++;
				ccheck[14] = 1;
				missCheck[14] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c4 && check2[15] == 1 && ccheck[15] != 1 && cruiser <= 1){
				c4.setBackground(Color.RED);
				cruiser++;
				ccheck[15] = 1;
				missCheck[15] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c5 && check2[16] == 1 && ccheck[16] != 1 && cruiser <= 1){
				c5.setBackground(Color.RED);
				cruiser++;
				ccheck[16] = 1;
				missCheck[16] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == c6 && check2[17] == 1 && ccheck[17] != 1 && cruiser <= 1){
				c6.setBackground(Color.RED);
				cruiser++;
				ccheck[17] = 1;
				missCheck[17] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			
			else if(e.getSource() == d1 && check2[18] == 1 && ccheck[18] != 1 && cruiser <= 1){
				d1.setBackground(Color.RED);
				cruiser++;
				ccheck[18] = 1;
				missCheck[18] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d2 && check2[19] == 1 && ccheck[19] != 1 && cruiser <= 1){
				d2.setBackground(Color.RED);
				cruiser++;
				ccheck[19] = 1;
				missCheck[19] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d3 && check2[20] == 1 && ccheck[20] != 1 && cruiser <= 1){
				d3.setBackground(Color.RED);
				cruiser++;
				ccheck[20] = 1;
				missCheck[20] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d4 && check2[21] == 1 && ccheck[21] != 1 && cruiser <= 1){
				d4.setBackground(Color.RED);
				cruiser++;
				ccheck[21] = 1;
				missCheck[21] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d5 && check2[22] == 1 && ccheck[22] != 1 && cruiser <= 1){
				d5.setBackground(Color.RED);
				cruiser++;
				ccheck[22] = 1;
				missCheck[22] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == d6 && check2[23] == 1 && ccheck[23] != 1 && cruiser <= 1){
				d6.setBackground(Color.RED);
				cruiser++;
				ccheck[23] = 1;
				missCheck[23] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e1 && check2[24] == 1 && ccheck[24] != 1 && cruiser <= 1){
				e1.setBackground(Color.RED);
				cruiser++;
				ccheck[24] = 1;
				missCheck[24] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e2 && check2[25] == 1 && ccheck[25] != 1 && cruiser <= 1){
				e2.setBackground(Color.RED);
				cruiser++;
				ccheck[25] = 1;
				missCheck[25] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e3 && check2[26] == 1 && ccheck[26] != 1 && cruiser <= 1){
				e3.setBackground(Color.RED);
				cruiser++;
				ccheck[26] = 1;
				missCheck[26] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e4 && check2[27] == 1 && ccheck[27] != 1 && cruiser <= 1){
				e4.setBackground(Color.RED);
				cruiser++;
				ccheck[27] = 1;
				missCheck[27] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e5 && check2[28] == 1 && ccheck[28] != 1 && cruiser <= 1){
				e5.setBackground(Color.RED);
				cruiser++;
				ccheck[28] = 1;
				missCheck[28] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == e6 && check2[29] == 1 && ccheck[29] != 1 && cruiser <= 1){
				e6.setBackground(Color.RED);
				cruiser++;
				ccheck[29] = 1;
				missCheck[29] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
				
			else if(e.getSource() == f1 && check2[30] == 1 && ccheck[30] != 1 && cruiser <= 1){
				f1.setBackground(Color.RED);
				cruiser++;
				ccheck[30] = 1;
				missCheck[30] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f2 && check2[31] == 1 && ccheck[31] != 1 && cruiser <= 1){
				f2.setBackground(Color.RED);
				cruiser++;
				ccheck[31] = 1;
				missCheck[31] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f3 && check2[32] == 1 && ccheck[32] != 1 && cruiser <= 1){
				f3.setBackground(Color.RED);
				cruiser++;
				ccheck[32] = 1;
				missCheck[32] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f4 && check2[33] == 1 && ccheck[33] != 1 && cruiser <= 1){
				f4.setBackground(Color.RED);
				cruiser++;
				ccheck[33] = 1;
				missCheck[33] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f5  && check2[34] == 1 && ccheck[34] != 1 && cruiser <= 1){
				f5.setBackground(Color.RED);
				cruiser++;
				ccheck[34] = 1;
				missCheck[34] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else if(e.getSource() == f6 && check2[35] == 1 && ccheck[35] != 1 && cruiser <= 1){
				f6.setBackground(Color.RED);
				cruiser++;
				ccheck[35] = 1;
				missCheck[35] = 1;
				count++;
				clicks = Integer.toString(count);
				clickNum.setText(clicks);
				hitMiss.setText("HIT!");
			}
			else{
				hitMiss.setText("MISS!");
				if(e.getSource() == a1 && missCheck[0] == 0){
					a1.setBackground(Color.YELLOW);
					count++;
					missCheck[0] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == a2 && missCheck[1] == 0){
					a2.setBackground(Color.YELLOW);
					count++;
					missCheck[1] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == a3 && missCheck[2] == 0){
					a3.setBackground(Color.YELLOW);
					count++;
					missCheck[2] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == a4 && missCheck[3] == 0){
					a4.setBackground(Color.YELLOW);
					count++;
					missCheck[3] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == a5 && missCheck[4] == 0){
					a5.setBackground(Color.YELLOW);
					count++;
					missCheck[4] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == a6 && missCheck[5] == 0){
					a6.setBackground(Color.YELLOW);
					count++;
					missCheck[5] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				if(e.getSource() == b1 && missCheck[6] ==0){
					b1.setBackground(Color.YELLOW);
					count++;
					missCheck[6] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == b2 && missCheck[7] == 0){
					b2.setBackground(Color.YELLOW);
					count++;
					missCheck[7] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == b3 && missCheck[8] == 0){
					b3.setBackground(Color.YELLOW);
					count++;
					missCheck[8] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == b4 && missCheck[9] == 0){
					b4.setBackground(Color.YELLOW);
					count++;
					missCheck[9] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == b5 && missCheck[10] == 0){
					b5.setBackground(Color.YELLOW);
					count++;
					missCheck[10] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == b6 && missCheck[11] == 0){
					b6.setBackground(Color.YELLOW);
					count++;
					missCheck[11] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				if(e.getSource() == c1 && missCheck[12] == 0){
					c1.setBackground(Color.YELLOW);
					count++;
					missCheck[12] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == c2 && missCheck[13] == 0){
					c2.setBackground(Color.YELLOW);
					count++;
					missCheck[13] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == c3 && missCheck[14] == 0){
					c3.setBackground(Color.YELLOW);
					count++;
					missCheck[14] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == c4 && missCheck[15] == 0){
					c4.setBackground(Color.YELLOW);
					count++;
					missCheck[15] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == c5 && missCheck[16] == 0){
					c5.setBackground(Color.YELLOW);
					count++;
					missCheck[16] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == c6 && missCheck[17] == 0){
					c6.setBackground(Color.YELLOW);
					count++;
					missCheck[17] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == d1 && missCheck[18] == 0){
					d1.setBackground(Color.YELLOW);
					count++;
					missCheck[18] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == d2 && missCheck[19] == 0){
					d2.setBackground(Color.YELLOW);
					count++;
					missCheck[19] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == d3 && missCheck[20] == 0){
					d3.setBackground(Color.YELLOW);
					count++;
					missCheck[20] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == d4 && missCheck[21] == 0){
					d4.setBackground(Color.YELLOW);
					count++;
					missCheck[21] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == d5 && missCheck[22] == 0){
					d5.setBackground(Color.YELLOW);
					count++;
					missCheck[22] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == d6 && missCheck[23] == 0){
					d6.setBackground(Color.YELLOW);
					count++;
					missCheck[23] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == e1 && missCheck[24] == 0){
					e1.setBackground(Color.YELLOW);
					count++;
					missCheck[24] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == e2 && missCheck[25] == 0){
					e2.setBackground(Color.YELLOW);
					count++;
					missCheck[25] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == e3 && missCheck[26] == 0){
					e3.setBackground(Color.YELLOW);
					count++;
					missCheck[26] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == e4 && missCheck[27] == 0){
					e4.setBackground(Color.YELLOW);
					count++;
					missCheck[27] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == e5 && missCheck[28] == 0){
					e5.setBackground(Color.YELLOW);
					count++;
					missCheck[28] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == e6 && missCheck[29] == 0){
					e6.setBackground(Color.YELLOW);
					count++;
					missCheck[29] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == f1 && missCheck[30] == 0){
					f1.setBackground(Color.YELLOW);
					count++;
					missCheck[30] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == f2 && missCheck[31] == 0){
					f2.setBackground(Color.YELLOW);
					count++;
					missCheck[31] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == f3 && missCheck[32] == 0){
					f3.setBackground(Color.YELLOW);
					count++;
					missCheck[32] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == f4 && missCheck[33] == 0){
					f4.setBackground(Color.YELLOW);
					count++;
					missCheck[33] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == f5 && missCheck[34] == 0){
					f5.setBackground(Color.YELLOW);
					count++;
					missCheck[34] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				else if(e.getSource() == f6 && missCheck[35] == 0){
					f6.setBackground(Color.YELLOW);
					count++;
					missCheck[35] = 1;
					clicks = Integer.toString(count);
					clickNum.setText(clicks);
				}
				
			}
			
			if(aircraftCarrier == 3){
				aircraftCarrier++;
				afloat.setSelected(false);
				asunk.setSelected(true);
				hitMiss.setText("You sunk enemy Aircraft Carrier!");
				winCount++;

				if(check[0] == 1){
					a1.setBackground(Color.BLACK);
				}
				if(check[1] == 1){
					a2.setBackground(Color.BLACK);
				}
				if(check[2] == 1){
					a3.setBackground(Color.BLACK);
				}
				if(check[3] == 1){
					a4.setBackground(Color.BLACK);
				}
				if(check[4] == 1){
					a5.setBackground(Color.BLACK);
				}
				if(check[5] == 1){
					a6.setBackground(Color.BLACK);
				}
				if(check[6] == 1){
					b1.setBackground(Color.BLACK);
				}
				if(check[7] == 1){
					b2.setBackground(Color.BLACK);
				}
				if(check[8] == 1){
					b3.setBackground(Color.BLACK);
				}
				if(check[9] == 1){
					b4.setBackground(Color.BLACK);
				}
				if(check[10] == 1){
					b5.setBackground(Color.BLACK);
				}
				if(check[11] == 1){
					b6.setBackground(Color.BLACK);
				}
				if(check[12] == 1){
					c1.setBackground(Color.BLACK);
				}
				if(check[13] == 1){
					c2.setBackground(Color.BLACK);
				}
				if(check[14] == 1){
					c3.setBackground(Color.BLACK);
				}
				if(check[15] == 1){
					c4.setBackground(Color.BLACK);
				}
				if(check[16] == 1){
					c5.setBackground(Color.BLACK);
				}
				if(check[17] == 1){
					c6.setBackground(Color.BLACK);
				}
				if(check[18] == 1){
					d1.setBackground(Color.BLACK);
				}
				if(check[19] == 1){
					d2.setBackground(Color.BLACK);
				}
				if(check[20] == 1){
					d3.setBackground(Color.BLACK);
				}
				if(check[21] == 1){
					d4.setBackground(Color.BLACK);
				}
				if(check[22] == 1){
					d5.setBackground(Color.BLACK);
				}
				if(check[23] == 1){
					d6.setBackground(Color.BLACK);
				}
				if(check[24] == 1){
					e1.setBackground(Color.BLACK);
				}
				if(check[25] == 1){
					e2.setBackground(Color.BLACK);
				}
				if(check[26] == 1){
					e3.setBackground(Color.BLACK);
				}
				if(check[27] == 1){
					e4.setBackground(Color.BLACK);
				}
				if(check[28] == 1){
					e5.setBackground(Color.BLACK);
				}
				if(check[29] == 1){
					e6.setBackground(Color.BLACK);
				}
				if(check[30] == 1){
					f1.setBackground(Color.BLACK);
				}
				if(check[31] == 1){
					f2.setBackground(Color.BLACK);
				}
				if(check[32] == 1){
					f3.setBackground(Color.BLACK);
				}
				if(check[33] == 1){
					f4.setBackground(Color.BLACK);
				}
				if(check[34] == 1){
					f5.setBackground(Color.BLACK);
				}
				if(check[35] == 1){
					f6.setBackground(Color.BLACK);
				}
			}
			else if(destroyer == 2){
				destroyer++;
				dfloat.setSelected(false);
				dsunk.setSelected(true);
				hitMiss.setText("You sunk enemy destroyer!");
				winCount++;
				
				if(check1[0] == 1){
					a1.setBackground(Color.BLACK);
				}
				if(check1[1] == 1){
					a2.setBackground(Color.BLACK);
				}
				if(check1[2] == 1){
					a3.setBackground(Color.BLACK);
				}
				if(check1[3] == 1){
					a4.setBackground(Color.BLACK);
				}
				if(check1[4] == 1){
					a5.setBackground(Color.BLACK);
				}
				if(check1[5] == 1){
					a6.setBackground(Color.BLACK);
				}
				if(check1[6] == 1){
					b1.setBackground(Color.BLACK);
				}
				if(check1[7] == 1){
					b2.setBackground(Color.BLACK);
				}
				if(check1[8] == 1){
					b3.setBackground(Color.BLACK);
				}
				if(check1[9] == 1){
					b4.setBackground(Color.BLACK);
				}
				if(check1[10] == 1){
					b5.setBackground(Color.BLACK);
				}
				if(check1[11] == 1){
					b6.setBackground(Color.BLACK);
				}
				if(check1[12] == 1){
					c1.setBackground(Color.BLACK);
				}
				if(check1[13] == 1){
					c2.setBackground(Color.BLACK);
				}
				if(check1[14] == 1){
					c3.setBackground(Color.BLACK);
				}
				if(check1[15] == 1){
					c4.setBackground(Color.BLACK);
				}
				if(check1[16] == 1){
					c5.setBackground(Color.BLACK);
				}
				if(check1[17] == 1){
					c6.setBackground(Color.BLACK);
				}
				if(check1[18] == 1){
					d1.setBackground(Color.BLACK);
				}
				if(check1[19] == 1){
					d2.setBackground(Color.BLACK);
				}
				if(check1[20] == 1){
					d3.setBackground(Color.BLACK);
				}
				if(check1[21] == 1){
					d4.setBackground(Color.BLACK);
				}
				if(check1[22] == 1){
					d5.setBackground(Color.BLACK);
				}
				if(check1[23] == 1){
					d6.setBackground(Color.BLACK);
				}
				if(check1[24] == 1){
					e1.setBackground(Color.BLACK);
				}
				if(check1[25] == 1){
					e2.setBackground(Color.BLACK);
				}
				if(check1[26] == 1){
					e3.setBackground(Color.BLACK);
				}
				if(check1[27] == 1){
					e4.setBackground(Color.BLACK);
				}
				if(check1[28] == 1){
					e5.setBackground(Color.BLACK);
				}
				if(check1[29] == 1){
					e6.setBackground(Color.BLACK);
				}
				if(check1[30] == 1){
					f1.setBackground(Color.BLACK);
				}
				if(check1[31] == 1){
					f2.setBackground(Color.BLACK);
				}
				if(check1[32] == 1){
					f3.setBackground(Color.BLACK);
				}
				if(check1[33] == 1){
					f4.setBackground(Color.BLACK);
				}
				if(check1[34] == 1){
					f5.setBackground(Color.BLACK);
				}
				if(check1[35] == 1){
					f6.setBackground(Color.BLACK);
				}
			}
			else if(cruiser == 1){
				cruiser++;
				hitMiss.setText("You sunk enemy cruiser!");
				cfloat.setSelected(false);
				csunk.setSelected(true);
				winCount++;
				
				if(check2[0] == 1){
					a1.setBackground(Color.BLACK);
				}
				if(check2[1] == 1){
					a2.setBackground(Color.BLACK);
				}
				if(check2[2] == 1){
					a3.setBackground(Color.BLACK);
				}
				if(check2[3] == 1){
					a4.setBackground(Color.BLACK);
				}
				if(check2[4] == 1){
					a5.setBackground(Color.BLACK);
				}
				if(check2[5] == 1){
					a6.setBackground(Color.BLACK);
				}
				if(check2[6] == 1){
					b1.setBackground(Color.BLACK);
				}
				if(check2[7] == 1){
					b2.setBackground(Color.BLACK);
				}
				if(check2[8] == 1){
					b3.setBackground(Color.BLACK);
				}
				if(check2[9] == 1){
					b4.setBackground(Color.BLACK);
				}
				if(check2[10] == 1){
					b5.setBackground(Color.BLACK);
				}
				if(check2[11] == 1){
					b6.setBackground(Color.BLACK);
				}
				if(check2[12] == 1){
					c1.setBackground(Color.BLACK);
				}
				if(check2[13] == 1){
					c2.setBackground(Color.BLACK);
				}
				if(check2[14] == 1){
					c3.setBackground(Color.BLACK);
				}
				if(check2[15] == 1){
					c4.setBackground(Color.BLACK);
				}
				if(check2[16] == 1){
					c5.setBackground(Color.BLACK);
				}
				if(check2[17] == 1){
					c6.setBackground(Color.BLACK);
				}
				if(check2[18] == 1){
					d1.setBackground(Color.BLACK);
				}
				if(check2[19] == 1){
					d2.setBackground(Color.BLACK);
				}
				if(check2[20] == 1){
					d3.setBackground(Color.BLACK);
				}
				if(check2[21] == 1){
					d4.setBackground(Color.BLACK);
				}
				if(check2[22] == 1){
					d5.setBackground(Color.BLACK);
				}
				if(check2[23] == 1){
					d6.setBackground(Color.BLACK);
				}
				if(check2[24] == 1){
					e1.setBackground(Color.BLACK);
				}
				if(check2[25] == 1){
					e2.setBackground(Color.BLACK);
				}
				if(check2[26] == 1){
					e3.setBackground(Color.BLACK);
				}
				if(check2[27] == 1){
					e4.setBackground(Color.BLACK);
				}
				if(check2[28] == 1){
					e5.setBackground(Color.BLACK);
				}
				if(check2[29] == 1){
					e6.setBackground(Color.BLACK);
				}
				if(check2[30] == 1){
					f1.setBackground(Color.BLACK);
				}
				if(check2[31] == 1){
					f2.setBackground(Color.BLACK);
				}
				if(check2[32] == 1){
					f3.setBackground(Color.BLACK);
				}
				if(check2[33] == 1){
					f4.setBackground(Color.BLACK);
				}
				if(check2[34] == 1){
					f5.setBackground(Color.BLACK);
				}
				if(check2[35] == 1){
					f6.setBackground(Color.BLACK);
				}
			}
			
			if(winCount == 3){
				JOptionPane.showMessageDialog(null, "You Sunk All Enemy Ships!");
				System.exit(0);
			}
		}
		
		public void mouseEntered(MouseEvent e) 
		{
		}

		public void mouseExited(MouseEvent e) 
		{
		}

		public void mousePressed(MouseEvent e) 
		{
		}

		public void mouseReleased(MouseEvent e) 
		{	
		}
		
	}
	
	public static void main(String Args[])
	{
		new Battleship();
	}

}