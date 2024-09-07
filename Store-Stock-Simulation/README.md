# My Personal Project

## Proposal
For my CPSC 210 term project, I am creating an application that keeps track of products in stock for a store or e-commerce. The application will keep track of the amount of products that are in stock by allowing the user to **add new products** and **remove old items**. Each product will store basic details like name, price, and quantity. Users are able to monitor stock levels and organize all store inventory in one place. This application can be used by store managers and staff that are in charge of maintaining inventory. Also, it can be helpful for e-commerce business owners overseeing stock levels. This project is an interest for me because I have an interest in e-commerce and how other big e-commerce cites like Shoplify work.

## User Stories
- I want to be able to select a product and view the name, price, and quantity of the product
- I want to be able to add multiple new products to my list of products and specify the name, price, and quantity
- I want to be able to view and list all products in different in-stock and out-of-stock lists
- I want to be able to remove the product from the list of product
- I want to be able to save my list of products and its description to file
- I want to be able to be able to load my list of products and its description from file

## Instructions for Grader
- You can generate the first required action related to the user story "add multiple new products to my list of products" by running the application and clicking on add products. Then, enter the required textfields and press confirm to add the product to the list of products. Repeat the last step to add multiple products to list.
- You can generate the second required action related to the user story "to view and list all products in different in-stock and out-of-stock lists" by running the application and clicking on check stock. Then, you will see two lists of in stock and out of stock products seperated by dotted line in the middle.
- You can locate my visual component by running the application and the picture will be on the homePanel.
- You can save the state of my application by running the application and clicking on save.
- You can reload the state of my application by running the application and clicking on load.

## Phase 4: Task 2
Wed Aug 07 19:00:17 PDT 2024
Added milk to MegaStore
Wed Aug 07 19:00:24 PDT 2024
Added candy to MegaStore
Wed Aug 07 19:00:39 PDT 2024
Added chips to MegaStore
Wed Aug 07 19:00:51 PDT 2024
Removed milk from MegaStore

## Phase 4: Task 3
After drawing my UML diagram, I noticed that I only have one class called StoreGUI thats responsible for the UI of this application. This can be a problem as my StoreGUI class has almost 400 lines of code which can be difficult to read and find code. This can be refactored into many different classes for each panel and frame and also include the use of interfaces and abstract classes. Doing this can help with organization of the overall structure. Furthermore, the code will be easier to read for other users and smoother access to edit each panel or frame.