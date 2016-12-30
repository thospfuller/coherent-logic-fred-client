def opts = '''freq
gen
geo
geot
rls
seas
src'''

String enumName = "TagGroupId"

opts.eachLine {

    def method = """
    /**
     * @see {@link #with$enumName($enumName)}
     * @see {@link $enumName#${it}}
     */
    public QueryBuilder with${enumName}As${it.capitalize()} () {
        return with$enumName ($enumName.${it});
    }"""

    println method
}