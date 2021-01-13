In this project, I have made a simple kiosk application. 

![Kiosk Main](https://user-images.githubusercontent.com/57363879/104501049-1eeb5180-55d7-11eb-972f-c3031abc3c62.PNG)

This application enables customers to select items in stock, enter how many they would like and add it to the basket. when they are happy that they have got everything they need, a total price is calculated using the quantity and the item price. The customer can selected which payment method they require, cash or card.

![Cash](https://user-images.githubusercontent.com/57363879/104501068-24e13280-55d7-11eb-8d17-2d13507de72c.PNG)

The cash method, enables the user to see the total and enter any amount; if the amount of money they have entered is less than the total, the amount they entered is deducted from the total and the user is prompted of how much they have got to pay. However if they enter the correct amount or an greater amount, their change is caluclated and they can print their receipt. The receipt contains the shop name, the date, the items bought, and any change given.

![card](https://user-images.githubusercontent.com/57363879/104501070-2579c900-55d7-11eb-9fb7-40258d33a64a.PNG)

The card is similar to teh cash but when the user clicks on the pay button a switch case determines if their card is verified by the bank or not, if it is then they can print their receipt.

![Receipt](https://user-images.githubusercontent.com/57363879/104501098-2ca0d700-55d7-11eb-8af7-8e1bd4bfaa98.PNG)

When the customer finishes their shopping the quantity of items that they have bought, is deducted from the quantity in the stock. 

![AdminLogin](https://user-images.githubusercontent.com/57363879/104501017-1561e980-55d7-11eb-96b3-c591a820b443.PNG)

When the kiosk first boots up, their is an option to enable the admin to log in and view the current stock in the database. when they get to the stock admin page they can add, delete or reorder/edit stock if the quantity is below 5.

