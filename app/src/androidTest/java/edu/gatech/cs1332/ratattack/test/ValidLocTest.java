package edu.gatech.cs1332.ratattack.test;

import org.junit.Test;

import static org.junit.Assert.*;

import edu.gatech.cs1332.ratattack.model.Rat;

/**
 * Created by Andy on 11/10/2017.
 */

public class ValidLocTest {

    @Test
    public void validTrueTest() {
        Rat test = new Rat("test", "test", "test", "test", "test", "test", "test", "0", "0");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "90", "180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "-90", "-180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "90", "-180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "-90", "180");
        assertTrue(test.validLoc());
    }

    @Test
    public void validTrueTestBoundry() {
        Rat test = new Rat("test", "test", "test", "test", "test", "test", "test", "90", "0");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "-90", "0");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "0", "180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "0", "-180");
        assertTrue(test.validLoc());
    }

    @Test
    public void validTrueTestCorner() {
        Rat test = new Rat("test", "test", "test", "test", "test", "test", "test", "90", "180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "-90", "-180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "90", "-180");
        assertTrue(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "-90", "180");
        assertTrue(test.validLoc());
    }

    @Test
    public void emptyTest() {
        Rat test = new Rat("test", "test", "test", "test", "test", "test", "test", "", "");
        assertFalse(test.validLoc());
    }

    @Test
    public void nonParsableTest() {
        Rat test = new Rat("test", "test", "test", "test", "test", "test", "test", "abc", "abc");
        assertFalse(test.validLoc());
    }

    @Test
    public void outOfBoundTest() {
        Rat test = new Rat("test", "test", "test", "test", "test", "test", "test", "0", "181");
        assertFalse(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "91", "0");
        assertFalse(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "0", "-181");
        assertFalse(test.validLoc());
        test = new Rat("test", "test", "test", "test", "test", "test", "test", "-91", "0");
        assertFalse(test.validLoc());
    }



}
