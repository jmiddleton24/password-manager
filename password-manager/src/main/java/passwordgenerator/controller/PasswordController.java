/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import passwordgenerator.model.PasswordModel;
import passwordgenerator.view.AbstractView;
import passwordgenerator.view.MainView;

/**
 *
 * @author BMamba2942
 */
public class PasswordController extends AbstractController{
    
    private ArrayList<String> passwords;
    private PasswordModel pModel;
    
    public PasswordController()
    {
    	
    }
    
    public PasswordController(ArrayList<String> passwords)
    {
        this.passwords = passwords;
        pModel = new PasswordModel(passwords);
        setModel(pModel);
        setView(new MainView((PasswordModel)getModel(), this));
        ((AbstractView)getView()).setVisible(true);
    }
    
    public void operation(String option, int index, File file)
    {
        /* When a button is pressed, it will pass its name as the option variable*/
        switch(option)
        {
            case MainView.ADD:
                new AddPController(pModel, MainView.ADD);
                break;
            case MainView.ADD_OWN:
                new AddPController(pModel, MainView.ADD_OWN);
                break;
            case MainView.COPY:
                try
                {
                    String temp = ((PasswordModel)getModel()).getPassword(index).getPasswordString();
                    StringSelection stringSelection = new StringSelection(temp);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    //No password there, so just do nothing
                }
                break;
            case MainView.REMOVE: //TODO: confirm the password to be removed first
            	try
            	{
                   if(JOptionPane.showConfirmDialog(null, 
                    "Are you sure you wish to remove " + pModel.getPassword(index).getPasswordName() +"?", "Remove Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                	   pModel.removePassword(index);
            	}
            	catch(ArrayIndexOutOfBoundsException e)
            	{
            		JOptionPane.showMessageDialog((AbstractView)getView(), "No passwords to remove, list is empty.");
            	}
            	break;
            case MainView.EXIT:
            	pModel.savePasswords(file);
            	System.exit(0);
            	break;
        }
    }
}