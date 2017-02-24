--------OS RESTRICTIONS--------------------------------------
WINDOWS: needs Eclipse IDE
MACOS: needs xterm
Linux: none

--------TO COMPILE-------------------------------------------
NEEDS: ant
RUN: 'ant compile'

--------TO RUN APPLICATION-----------------------------------
NEEDS: ant
RUN: 'ant runapp'
LOGIN:
    username: testuser
    pw: test123

DETAILED DIRECTIONS: unzip the application folder. Open shell and navigate to '$(PWD)/cs48-scheduler/'.
Run `ant runapp` in shell. Login with user credentials listed above. Click login.
Add and remove events at will. Any runtime exceptions thrown will be listed in shell and do nothing to
change application state.


--------KNOWN BUGS-------------------------------------------
-> Changes made to a logged in user is not persistent.
