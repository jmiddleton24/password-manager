/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import passwordgenerator.model.PasswordModel;
import passwordgenerator.util.EmptyStringException;
import passwordgenerator.util.Password;
import passwordgenerator.util.SpaceException;
import passwordgenerator.view.AbstractView;
import passwordgenerator.view.AddOwnView;
import passwordgenerator.view.AddView;
import passwordgenerator.view.MainView;

/**
 *
 * @author BMamba2942
 */
public class AddPController extends PasswordController {
	private PasswordModel passwords;
	private Boolean caughtError;
	private Password pass, ownPass;

	public AddPController(PasswordModel passwords, String mode)
	{
		this.passwords = passwords;
		setModel(this.passwords);
		if(mode.equals(MainView.ADD))
		   setView(new AddView(this.passwords, this));
		else if(mode.equals(MainView.ADD_OWN))
			setView(new AddOwnView(this.passwords, this));
		((AbstractView)getView()).setVisible(true);
	}

	public void operation(String option, String name, int length, String userPass)
	{
		switch(option)
		{
		case AddView.GEN:
			try
			{
			   pass = new Password();
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog((AbstractView)getView(), "Unable to open a file to save passwords."); //TODO: HAVE USER CHECK WHETHER THEY MAY WRITE TO THAT DIRECTORY
			}
			caughtError = false;
			try
			{
				this.passwords.add(pass.generate(length, name));
			}
			catch(EmptyStringException e)
			{
				JOptionPane.showMessageDialog((AbstractView)getView(), "Please enter a password name");
				caughtError = true;
			}
			catch(SpaceException f)
			{
				JOptionPane.showMessageDialog((AbstractView)getView(), "Please do not put any spaces");
				caughtError = true;
			}
			if(caughtError)
				((AbstractView)getView()).setVisible(true); //If an exception was caught, leave view open so user may make change
			else
				((AbstractView)getView()).setVisible(false); //otherwise, close view
			break;
		case AddOwnView.ADD:
			try
			{
			    ownPass = new Password();
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog((AbstractView)getView(), "Unable to open a file to save passwords."); //TODO: HAVE USER CHECK WHETHER THEY MAY WRITE TO THAT DIRECTORY
			}
			caughtError = false;
			try
			{
				this.passwords.add(ownPass.createPassword(userPass, name));
			}
			catch(EmptyStringException e)
			{
				JOptionPane.showMessageDialog((AbstractView)getView(), "Please enter a password name");
				caughtError = true;
			}
			catch(SpaceException f)
			{
				JOptionPane.showMessageDialog((AbstractView)getView(), "Please do not put any spaces.");
				caughtError = true;
			}
			if(caughtError)
				((AbstractView)getView()).setVisible(true); //If an exception was caught, leave view open so user may make change
			else
				((AbstractView)getView()).setVisible(false); //otherwise, close view
			break;
		}


	}

	private void openView()
	{
		setModel(this.passwords);
		setView(new AddOwnView(this.passwords, this));
		((AbstractView)getView()).setVisible(true);
	}

}

