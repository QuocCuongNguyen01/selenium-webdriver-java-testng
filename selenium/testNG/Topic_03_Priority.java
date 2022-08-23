package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Priority {
  @Test(enabled = false)
  public void EndUser_01_Register_New_Employee() {
	  
  }
  @Test(description = "PM - 003")
  public void EndUser_02_View_Employee() {
	  Assert.assertTrue(false);
  }
  @Test(enabled = false)
  public void EndUser_03_Edit_Employee() {
  } 
  @Test(enabled = false)
  public void EndUser__04Move_Employee() {
  }
}