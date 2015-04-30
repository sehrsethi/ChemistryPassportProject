package apple_kit;

import javax.swing.JComponent;

import main.ChemistryPassportGUI;
import kit_interfaces.Kit;

public class AppleKit extends Kit{

	public AppleKit(ChemistryPassportGUI mainGUI) {
		super(mainGUI);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUserKitProgress(int progress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startKit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getButtonName() {
		// TODO Auto-generated method stub
		return "Apple Kit";
	}

	@Override
	public JComponent createRewardGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRewardName() {
		// TODO Auto-generated method stub
		return null;
	}

}
