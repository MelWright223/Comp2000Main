package com.View;

import com.Model.Kiosk;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public interface IKiosk {


    default void scannedItems(final List<Integer> itemCode) {

    }

}
