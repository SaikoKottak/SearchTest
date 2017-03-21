package com.nautrainee.search;

public class ClassFile implements Comparable<ClassFile> {
    public final String className;
    public final long modificationDate;

    public ClassFile(String className, long modificationDate) {
        this.className = className;
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassFile classFile = (ClassFile) o;

        if (modificationDate != classFile.modificationDate) return false;
        return className != null ? className.equals(classFile.className) : classFile.className == null;

    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (int) (modificationDate ^ (modificationDate >>> 32));
        return result;
    }

    @Override
    public int compareTo(ClassFile o) {
        if (modificationDate != o.modificationDate) {
            return Long.compare(o.modificationDate, modificationDate);
        } else if (className == null) {
            return o.className == null ? 0 : 1;
        } else {
            return className.compareTo(o.className);
        }
    }

    public String getClassName() {
        return className;
    }

}