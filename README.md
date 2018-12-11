# Onetime Password System
An application for my CSS337 class (Secure Systems).

### Project Objective:
To create an event-based onetime password.

###### Components
* A soft OTP token UI which consists of a push button and a display control. Clicking on the button will generate and display the onetime password.
* A test UI which will consists of a password entry box. Prompts the user to provide the OTP, shows access granted message only if the right OTP is entered.

###### Deployment
* Run testUI.java first (server), then run softTokenUI.java (client)
* Both components are designed to be run on the same device. Port numbers are hardcoded.

###### Notes
* [SHA2](https://en.wikipedia.org/wiki/SHA-2) used to generate password
* Uses client-server networking to connect password generator to password tester
* See [Assignment_2.pdf](https://github.com/GGoziker/Onetime-Password-System/blob/master/Assignment_2.pdf) for password generation details
