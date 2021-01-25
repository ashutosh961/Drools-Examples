package org.drools.examples.Example;


import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;



/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class find_pair {

    static int[] array = new int[]{1, 2, 3, 4, 5, 6};

    public static final void main(final String[] args) {
        // From the kie services, a container is created from the classpath
        KieServices ks = KieServices.get();
        KieContainer kc = ks.getKieClasspathContainer();

        execute( ks, kc );
    }

    public static void execute( KieServices ks, KieContainer kc ) {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("Example1KS");

        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the
        // org/drools/examples/helloworld/HelloWorld.drl file
        ksession.setGlobal( "list", new ArrayList<Object>() );

        // The application can also setup listeners
        ksession.addEventListener( new DebugAgendaEventListener() );
        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        // Set up a file based audit logger
        KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./Example" );

        // To set up a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session

        for(int i=0; i<array.length-1; i++)
        {
            for(int j=0;j<array.length;j++)
            {
                Pair p = new Pair(array[i], array[j]);//Create pairs of objects
                ksession.insert(p);
                //Can we pass pairs of numbers as facts into the knowledge session?
                //Lets try
            }
        }

//        Pair =  new org.drools.examples.Example2.main.Message();
//        message.setMessage( "Hello World" );
//        message.setStatus( org.drools.examples.Example2.main.Message.HELLO );
//        ksession.insert( message );

        // and fire the rules
        ksession.fireAllRules();

        // Close logger
        logger.close();

        // and then dispose the session
        ksession.dispose();
    }




}


