/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Asmaa
 */
public class Message {
    String from;
    String to;
    XMLGregorianCalendar date;
    String content;
    String fontColor;
    String fontStyle;

    public String getContent() {
        return content;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public String getFontColor() {
        return fontColor;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public String getTo() {
        return to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    
}
