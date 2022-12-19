public class deneme {
    public static void main(String[] args)
    {
        String inputFile;
        File inputListStream;

    if (args.length == 1)
    {
        inputFile = args[0]; // Parse the command from running the program to get input file
    }
    else
    {
        printUsage(); // If the user did not specify a file, print the correct usage of the program, then exit
        System.exit(0);
    }

    try
    {
        inputListStream = new File(inputFile);
        // Open the file
    }
    catch (FileNotFoundException e)
    {
        System.out.println("Error opening file"); // If there was an error opening the file, print error and exit program
        System.exit(0);
    }

    fillInputQueue(inputFile, inputListStream); // Fill the input queue with new processes
    initializeSystem(); // Initialize the system

    while (!complete()) // Loop until all processes have finished
    {
        checkInputQueue(); // Remove processes from input queue and put in appropriate queue

        checkUserJobQueue(); // Remove processes from user job queue and put in appropriate queue

        if (currentProcess != null) // There is a process currently running, so handle it
        {
            handleCurrentProcess();
        }

        if ((realTimeQueue != null || priorityOneQueue != null || priorityTwoQueue != null || priorityThreeQueue != null) && (currentProcess == null)) // There are still jobs left to be completed
        {
            assignCurrentProcess();
        }

        try
        {
            Thread.sleep(1000); // Sleep 1 second to simulate 1 second of work
        }
        catch (InterruptedException e)
        {
            // Handle interrupted exception
        }

        timer += 1; // Add 1 second to the current time
    }

    System.out.println("All processes have finished.");
}

}