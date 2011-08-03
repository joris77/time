
                    package views.Application.html

                    import play.templates._
                    import play.templates.TemplateMagic._
                    import views.html._

                    object index extends BaseScalaTemplate[Html,Format[Html]](HtmlFormat) {

                        def apply/*1.2*/(title:String,year : Int, month : Int,timeSlots : Seq[nl.smartworkx.time.TimeSlot] = Nil):Html = {
                            try {
                                _display_ {

format.raw/*1.91*/("""

""")+_display_(/*3.2*/main(title,"")/*3.16*/ {format.raw/*3.18*/("""

<form id="searchForm" action="/Application/search" method="POST">
    <input id="searchYear" name="year" value="""")+_display_(/*6.48*/year)+format.raw/*6.52*/(""""/>
    <input id="searchMonth" name="month" value="""")+_display_(/*7.50*/month)+format.raw/*7.55*/(""""/>
    <input type="submit" value="Search">
</form>

<form id="createForm" action="/Application/create" method="POST"
      onsubmit="$('#createMonth').val($('#searchMonth').val());$('#createYear').val($('#searchYear').val());">
    <input id="createYear" type="hidden" name="year"/>
    <input id="createMonth" type="hidden" name="month"/>
    <input name="day"/>
    <input name="beginTimeHours"/>
    <input name="beginTimeMinutes"/>
    <input name="endTimeHours"/>
    <input name="endTimeMinutes"/>
    <input name="description">
    <input type="submit" value="New">
</form>

<ul>
    """)+_display_(/*25.6*/timeSlots/*25.15*/.map/*25.19*/ { t =>format.raw/*25.26*/("""
    <li>""")+_display_(/*26.10*/t)+format.raw/*26.11*/("""
        <button  onclick="$.ajax(""")+format.raw("""{""")+format.raw/*27.35*/("""
        type: 'DELETE',
        url: '/TimeSlot/""")+_display_(/*29.26*/t/*29.27*/.id)+format.raw/*29.30*/("""'""")+format.raw("""}""")+format.raw/*29.32*/(""");$(this).parent().remove()">Delete</button>
    </li>
    """)})+format.raw/*31.6*/("""
</ul>

""")})}
                            } catch {
                                case e:TemplateExecutionError => throw e
                                case e => throw Reporter.toHumanException(e)
                            }
                        }

                    }

                
                /*
                    -- GENERATED --
                    DATE: Wed Aug 03 00:18:31 CEST 2011
                    SOURCE: /app/views/Application/index.scala.html
                    HASH: dafdb3441c1f1b5319026a7678e751af463cf152
                    MATRIX: 329->1|525->90|553->93|575->107|595->109|736->224|760->228|839->281|864->286|1484->880|1502->889|1515->893|1541->900|1578->910|1600->911|1682->946|1759->996|1769->997|1793->1000|1842->1002|1930->1062
                    LINES: 10->1|14->1|16->3|16->3|16->3|19->6|19->6|20->7|20->7|38->25|38->25|38->25|38->25|39->26|39->26|40->27|42->29|42->29|42->29|42->29|44->31
                    -- GENERATED --
                */
            
