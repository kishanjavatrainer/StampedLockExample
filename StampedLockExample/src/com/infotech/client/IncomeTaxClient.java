package com.infotech.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.infotech.tax.IncomeTaxDept;
import com.infotech.tax.TaxPayer;

public class IncomeTaxClient {  
  private static final int NUMBER_OF_TAX_PAYER = 100; 
  private static IncomeTaxDept incomeTaxDept = new IncomeTaxDept(1000, NUMBER_OF_TAX_PAYER);  
  
  public static void main(String[] args) {
    registerTaxPayers();  
    ExecutorService executor = Executors.newFixedThreadPool(30);    
    System.out.println("Initial IncomeTax Department's total revenue is ===>> "+incomeTaxDept.getTotalRevenue());    
    for(final TaxPayer taxPayer : incomeTaxDept.getTaxPayersList())   
    {
    	executor.submit(() -> {             
        try {                 
          Thread.sleep(100);                 
          incomeTaxDept.payTax(taxPayer);                 
          double revenue = incomeTaxDept.getTotalRevenue();                                  
          System.out.println("IncomeTax Department's total revenue after tax paid at client code is --->> " +revenue);                                                   
          double returnAmount = incomeTaxDept.getFederalTaxReturn(taxPayer);                
          System.out.println(taxPayer.getTaxPayerName() + " received the Federal return amount = " +returnAmount);                                 
          revenue = incomeTaxDept.getTotalRevenueOptimisticRead();                
          System.out.println("IncomeTax Department's total revenue after getting Federal tax return at client code is --->> " +revenue);                                 
          double stateReturnAmount = incomeTaxDept.getStateTaxReturnUsingConvertToWriteLock(taxPayer);                                  
          System.out.println(taxPayer.getTaxPayerName() + " received the State tax return amount = " +stateReturnAmount);                                  
          revenue = incomeTaxDept.getTotalRevenueOptimisticRead();                 
          System.out.println("IncomeTax Department's total revenue after getting State tax return at client code is --->> " +revenue);                 
          Thread.sleep(100);             
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if(executor != null)
        	 executor.shutdown(); 
		}       
      }
    );  
  }
   
  }  
  private static void registerTaxPayers(){  
    for(int i = 1; i < NUMBER_OF_TAX_PAYER+1; i++){   
      TaxPayer taxPayer = new TaxPayer();    
      taxPayer.setTaxAmount(2000);   
      taxPayer.setTaxPayerName("Payer-"+i);   
      taxPayer.setTaxPayerSsn("xx-xxx-xxxx"+i);   
      incomeTaxDept.registerTaxPayer(taxPayer);  
    } 
  }
}
