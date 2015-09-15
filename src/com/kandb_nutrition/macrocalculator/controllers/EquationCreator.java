package com.kandb_nutrition.macrocalculator.controllers;


//	Using equation y = ax^2
//	Solved for a: a = y / x^2

public class EquationCreator 
{	
	private double power;
	private double root;
	private double aPower;
	private double aRoot;
	private double xSeconds;
	private double yMultiplier;
	
	public EquationCreator(double xSeconds, double yMultiplier)
	{
		power = 2.0;
		root = 2.0;
		aPower = yMultiplier / Math.pow(xSeconds, power);
		aRoot = yMultiplier / Math.pow(xSeconds, (1.0 / power));
	}
	
	public void setPower(double power)
	{
		this.power = power;
	}
	
	public void setAPower(double a)
	{
		this.aPower = a;
	}
	
	public double getPower()
	{
		return power;
	}
	
	public double getAPower()
	{
		return aPower;
	}
	
	public double getYMultiplier(double xSeconds)
	{
		return aPower * Math.pow(xSeconds, power);
	}
	
	public double getYMultiplierInverse(double xSeconds)
	{
		return aRoot * Math.pow(xSeconds, (1.0 / root));
		
	}
	
}
