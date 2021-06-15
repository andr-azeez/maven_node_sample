package com.hexaware.resortmanagement.util;

import java.util.Scanner;

/**
 * Main interface to the project.
 */
public class CliMain {
  private Scanner sc = new Scanner(System.in, "UTF-8");
  private void mainMenu() {
    System.out.println(" RESORT MANAGEMENT SYSTEM ");
    System.out.println(" ======================== ");
    System.out.println("1. I am a Member");
    System.out.println("2. I am an Employee");
    System.out.println("3. Exit");
    int ch = sc.nextInt();
    subMenu(ch);
  }

  private void subMenu(final int ch) {
    switch (ch) {
      case 1:
        MemberUtil mem = new MemberUtil();
        mem.memberMenu();
        break;
      case 2:
        EmployeeUtil emp = new EmployeeUtil();
        emp.empMenu();
        break;
      case 3:
        Runtime.getRuntime().exit(0);
      default:
        System.out.println("Wrong choice");
        break;
    }
    mainMenu();
  }

  /**
   * main method. Main entry point to the project.
   * @param args for command line arguments.
   */
  public static void main(final String[] args) {
    CliMain obj = new CliMain();
    obj.mainMenu();
  }
}
