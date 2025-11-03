package training.iqgateway.report;
//
//import java.util.logging.*;
//
//import org.eclipse.birt.core.framework.*;
//import org.eclipse.birt.report.engine.api.*;
//
//public class ExecuteReport {
//
//    // Generates a BIRT report given the report design path and output file path
//    public static void executeReport(String reportDesignPath,
//                                     String outputFilePath) throws EngineException {
//
//        IReportEngine engine = null;
//        EngineConfig config = null;
//
//        try {
//            // Configure BIRT engine
//            config = new EngineConfig();
//            config.setBIRTHome("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Softwares\\BIRT2\\birt-runtime-4_4_1\\ReportEngine");
//            config.setLogConfig("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Softwares\\BIRT2\\birt-runtime-4_4_1\\ReportEngine\\Log",
//                                Level.FINEST);
//
//            // Start BIRT platform
//            Platform.startup(config);
//
//            // Create the report engine
//            IReportEngineFactory factory =
//                (IReportEngineFactory)Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//            engine = factory.createReportEngine(config);
//
//            // Open the report design file
//            IReportRunnable design = engine.openReportDesign(reportDesignPath);
//
//            // Create a run and render task
//            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
//
//            // Validate parameters (add task.setParameterValue(...) if needed)
//            task.validateParameters();
//
//            // Set HTML render options
//            HTMLRenderOption htmlOptions = new HTMLRenderOption();
//            htmlOptions.setOutputFileName(outputFilePath);
//            htmlOptions.setOutputFormat("html");
//
//            // Set pdf render option
//
//            task.setRenderOption(htmlOptions);
//
//            // Run and render the report
//            task.run();
//            task.close();
//
//            // Destroy the engine after use
//            engine.destroy();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            // Always shut down the BIRT platform
//            Platform.shutdown();
//        }
//    }
//}
//


import java.util.logging.*;

import org.eclipse.birt.core.framework.*;
import org.eclipse.birt.report.engine.api.*;

public class ExecuteReport {
    final static String NAME = "Top Count";

    public static void executeReport(String value1,
                                     String value2) throws EngineException {

        IReportEngine engine = null;
        EngineConfig config = null;

        try {
            config = new EngineConfig();
            config.setBIRTHome("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Softwares\\BIRT2\\birt-runtime-4_4_1\\ReportEngine");
            config.setLogConfig("C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Softwares\\BIRT2\\birt-runtime-4_4_1\\ReportEngine\\Log",
                                Level.FINEST);
            Platform.startup(config);
            final IReportEngineFactory FACTORY =
                (IReportEngineFactory)Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = FACTORY.createReportEngine(config);

            // Open the report design
            IReportRunnable design = engine.openReportDesign(value1);
            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
            // task.setParameterValue("Top Count", new Integer(5));
            task.validateParameters();

            // Set PDF render options
            final PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
            PDF_OPTIONS.setOutputFileName(value2); // e.g., "output.pdf"
            PDF_OPTIONS.setOutputFormat("pdf");

            task.setRenderOption(PDF_OPTIONS);
            task.run();
            task.close();
            engine.destroy();
        } catch (final Exception EX) {
            EX.printStackTrace();
        } finally {
            Platform.shutdown();
        }
    }

    public static void executeReport1(String value1, String value2,
                                      String value3,
                                      String param) throws EngineException {

        IReportEngine engine = null;
        EngineConfig config = null;

        try {
            config = new EngineConfig();

            config.setBIRTHome("D:\\birt-runtime-4_4_1\\ReportEngine");

            config.setLogConfig("D:\\birt-runtime-4_4_1\\ReportEngine\\Log",
                                Level.FINEST);
            Platform.startup(config);
            final IReportEngineFactory FACTORY =
                (IReportEngineFactory)Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = FACTORY.createReportEngine(config);

            //open the report design

            IReportRunnable design = null;
            design = engine.openReportDesign(value1);

            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
            task.setParameterValue("Top Count", (new Integer(5)));
            task.validateParameters();


            // Set PDF render options
            final PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
            PDF_OPTIONS.setOutputFileName(value2); // e.g., "output.pdf"
            PDF_OPTIONS.setOutputFormat("pdf");

            task.setRenderOption(PDF_OPTIONS);
            task.setParameterValue(param, value3);
            //task.setParameterValue("PRC", "");

            task.run();
            task.close();
            engine.destroy();
        } catch (final Exception EX) {
            EX.printStackTrace();
        } finally {
            Platform.shutdown();
        }

    }

}
