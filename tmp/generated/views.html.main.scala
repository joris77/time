
                    package views.html

                    import play.templates._
                    import play.templates.TemplateMagic._
                    import views.html._

                    object main extends BaseScalaTemplate[Html,Format[Html]](HtmlFormat) {

                        def apply/*1.2*/(title:String = "", script : String = "")(body: => Html):Html = {
                            try {
                                _display_ {

format.raw/*1.58*/("""

<!DOCTYPE html>
<html>
    <head>
        <title>""")+_display_(/*6.17*/title)+format.raw/*6.22*/("""</title>
        <link rel="stylesheet" media="screen" href="""")+_display_(/*7.54*/asset("public/stylesheets/main.css"))+format.raw/*7.90*/("""">
        <link rel="shortcut icon" type="image/png" href="""")+_display_(/*8.59*/asset("public/images/favicon.png"))+format.raw/*8.93*/("""">
        <script src="""")+_display_(/*9.23*/asset("public/javascripts/jquery-1.5.2.min.js"))+format.raw/*9.70*/("""" type="text/javascript"></script>
        """)+_display_(/*10.10*/script)+format.raw/*10.16*/("""
    </head>
    <body>
        """)+_display_(/*13.10*/body)+format.raw/*13.14*/("""
    </body>
</html>
""")}
                            } catch {
                                case e:TemplateExecutionError => throw e
                                case e => throw Reporter.toHumanException(e)
                            }
                        }

                    }

                
                /*
                    -- GENERATED --
                    DATE: Mon Aug 01 01:00:25 CEST 2011
                    SOURCE: /app/views/main.scala.html
                    HASH: ceea2b843fa3bd9d6caf2155ebb0550716656bad
                    MATRIX: 316->1|479->57|557->109|582->114|670->176|726->212|813->273|867->307|918->332|985->379|1056->423|1083->429|1143->462|1168->466
                    LINES: 10->1|14->1|19->6|19->6|20->7|20->7|21->8|21->8|22->9|22->9|23->10|23->10|26->13|26->13
                    -- GENERATED --
                */
            
