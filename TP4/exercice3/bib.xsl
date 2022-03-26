<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="domain">
        <html>
            <body>
                <table border="1" width="100%">
                    <tbody>
                        <tr>
                            <td bgcolor="#c0c0c0" width="100%">
                                <h2>
                                    <xsl:value-of select="title"/>
                                </h2>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="bib_ref">
        <html>
            <body>
                <h3>
                    Titre: <xsl:value-of select="title"/>
                </h3>
                <p>
                    Auteur(s):  <xsl:value-of select="author"/>
                </p>
                <p>
                    Année:  <xsl:value-of select="year"/>
                </p>
                <p>Lien : 
                    <xsl:element name="a">
                        <xsl:attribute name="href">
                            <xsl:value-of select="weblink"/>
                        </xsl:attribute>
                        <xsl:value-of select="weblink"/>
                    </xsl:element>
                </p>
                <p>
                    Commentaire :  <xsl:value-of select="comment"/>
                </p>
                <hr></hr>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
