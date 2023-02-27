package org.example;

import org.example.DAO.PhoneDAO;
import org.example.Domain.Phone;

/**
 * Hello world!
 *
 */
public class Program
{

    public static void main( String[] args )
    {

        PhoneDAO servicePhoneDao = new PhoneDAO();


        System.out.println(servicePhoneDao.getPhoneHavePinkColorAndCostOver15Million());
        System.out.println(servicePhoneDao.getSellingHighestPrice());
        System.out.println(servicePhoneDao.getPhoneHaveFiftyMillionVND());
        System.out.println(servicePhoneDao.getPhoneSorted());
    }


}
