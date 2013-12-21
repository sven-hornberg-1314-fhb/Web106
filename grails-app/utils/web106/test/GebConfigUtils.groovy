package web106.test

/**
 * Created by marcman on 21.12.13.
 */
class GebConfigUtils {
    static void downloadDriver(File file, String path) {
        if (!file.exists()) {
            def ant = new AntBuilder()
            ant.get(src: path, dest: 'driver.zip')
            ant.unzip(src: 'driver.zip', dest: file.parent)
            ant.delete(file: 'driver.zip')
            ant.chmod(file: file, perm: '700')
        }
    }

}
