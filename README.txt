# cs48-scheduler
DelPlanner is an automated scheduling application that helps people create and coordinate events.
It uses a decentralized server/client model enabling connectivity to remote instances on any publicly accessible IP.
Create events, subscribe to events, and create conflict free schedules to help organize yourself.

# OS Requirements
macOS - java version 1.8

Windows -

Linux - java version 1.8

# Running the application
1. Unzip the DelPlanner.zip file.
2. Navigate into the directory the contents were extracted to.
3. From the terminal enter `java -jar DelPlanner.jar`

# Using the application
## Logging in
- On the right login panel, enter in user credentials and login (or create a new user).
- If a new user was created, credentials must be added in again to login.
- The login panel may be resized using the vertical slider bar in the middle of the screen, or minimized completely by clicking the right-pointing arrow on the slider bar.

## Managing Subscriptions
- The first tab on the left panel is the **My Events** tab and contains the information about your subscriptions and possible events.
- A **subscription** is simply an event that you plan to attend. These are displayed in the **My Events** list
- This tab is split into two halves, the left showing events you are currently subscribed to, the right showing possible events offered by an **Organization**
- To view possible events to subscribe to, select the **Organization** you wish to query from the drop down menu. The **Available Events** list will populate with the **Organization**'s events if a successful connection was made to the organization.
- Adding an event is simply done by selecting an event from the **Available Events** list and clicking **Add Event**.
- Removing a **subscription** is done by selecting an event from **My Events** list and clicking **Remove Event**.

### Generating a Schedule
- Once you have created a list of subscriptions, you can click **Generate Schedule** to create a schedule based on all the **subscriptions** in your **My Events** list.
- Schedules are one week in length and will contain subscriptions from your **My Events** list that do not have conflicting times.
- You will be prompted to enter a Month and Day, this represents the month and day that this schedule will start.
- Clicking **Generate** will create a Schedule and display it for you
- If you are satisfied with the result, you may click **Add Schedule** to save it for future viewing
_ If not, close the display and regenerate. The application will randomly create another Schedule that is possible based on your subscriptions.

## Managing Schedules
- The second tab displays all previous schedules that you have generated and saved. Select one from the drop down menu and it will be displayed.
- To remove a schedule, select it from the drop down menu and click **Delete Schedule**

## Managing Your Hosted Events
- The third tab displays information about your **hosted events**
- A **hosted event** is an event that you offer to other people. They can see it and subscribe to it (if you are running this application on a publicly accessible IP)
- **Hosted events** can be removed by selecting it on the left panel and clicking **Remove Hosted Event**
- To add a new **hosted event**, fill in the corresponding fields
..- The time fields must be given as comma separated list of all hours (1-24) the event occurs for the given display
..- For example, if the Event occurs Monday 9AM - 3PM  `9,10,11,12,13,14,15` must be entered into **Monday Times**

## Managing Organizations
- The third tab displays informations about **Organizations** you know of and would like to pull events from.
- An **Organization** is simply another User that uses this application at a remote IP address.
- Adding an **Organization** requires that you know the user's Username that they use at the remote application.
..- For example, if User cs48teacher uses DelPlanner at IP 192.168.0.1 you would enter `Organization Name: cs48teacher` `I.P. Address: 192.168.0.1`
- To remove an **organization**, select it and click **Remove Organization**.

# Saving your data
- To save all the information from your current session, **YOU MUST LOG OUT**.
..- If the application is closed before the current user logs out, any changes made in the current session is lost.
- This can be done by clicking **Log out** in the login panel.

# Known Errors:
- If the application is run for the first time, and closed immediately without adding a new user, it will fail to function properly for subsequent launches.
..- FIX: in the directory you are running from, delete `record.xml` and relaunch the application.
