package org.drools.examples.Example2;



import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;
import java.util.List;


public class RuleRunner {

    public RuleRunner() {
    }

    public void runRules(String[] rules,
                         List<Message> facts) {

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();


        for ( int i = 0; i < rules.length; i++ ) {
            String ruleFile = rules[i];
            System.out.println(ruleFile);
            System.out.println( "Loading file: " + ruleFile );
            kbuilder.add( ResourceFactory.newClassPathResource( ruleFile,
                    org.drools.examples.Example2.RuleRunner.class ),
                    ResourceType.DRL );
        }

        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages( pkgs );
        KieSession ksession = kbase.newKieSession();



        for ( int i = 0; i < facts.size(); i++ ) {
            Message fact = facts.get(i);
         //   System.out.println( "Pairs : " + "(" +fact.m + "," +fact.y + ")");
            ksession.insert( fact );
        }

        ksession.fireAllRules();
    }
}
