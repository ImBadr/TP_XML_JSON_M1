<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 
    <xsl:template match="/breakfast_menu/food">
        <html>
            <body>
                <div style="background-color : #008080; color: white">
                    <h2>
                        <xsl:value-of select="name"/>
                        <xsl:value-of select="price"/>
                    </h2>
                </div>
                <p>
                    <xsl:value-of select="description"/>
                    <xsl:if test="calories">
                        <xsl:value-of select="calories"/>
                        (calories per serving)
                    </xsl:if>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>