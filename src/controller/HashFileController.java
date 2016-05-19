package controller;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import main.Student;

/**
 * Bu arkadas ile excele yazma okuma islemlerini yapariz
 */
public class HashFileController
{
    public enum Sheets
    {
        midSquare,
        midSquareLinear,
        folding,
        foldingLinear,
        dividingTheRemaining,
        dividingTheRemainingLinear
    }

    public void createWorkBook(String name)
    {
        try
        {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(name));

            for (Sheets sheet : Sheets.values())
            {
                workbook.createSheet(sheet.toString(), sheet.ordinal());
            }

            workbook.write();
            workbook.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addCell(Sheets sheet, Long index, Student student)
    {
        Workbook workbook = null;
        try
        {
//            workbook = Workbook.getWorkbook(new File("output.xls"));
//            Sheet sheeet = workbook.getSheet(sheet.ordinal());
////            Label label = new  Label(0, index.intValue(), student.toString());
//            sheet.addCell(label);


            Workbook existingWorkbook = Workbook.getWorkbook(new File("output.xls"));
            WritableWorkbook workbookCopy = Workbook.createWorkbook(new File("output.xls"), existingWorkbook);
            WritableSheet sheetToEdit = workbookCopy.getSheet(sheet.name());

            Label label = new Label(0, index.intValue(), student.toString());
            sheetToEdit.addCell(label);
            workbookCopy.write();
            workbookCopy.close();
            existingWorkbook.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (BiffException e)
        {
            e.printStackTrace();
        }
        catch (RowsExceededException e)
        {
            e.printStackTrace();
        }
        catch (WriteException e)
        {
            e.printStackTrace();
        }

    }

    public Student getCell(Long index)
    {
        return new Student();
    }

}
