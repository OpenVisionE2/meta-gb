SUMMARY = "GigaBlue CFE AddOn"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "Gigablue"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/license-gplv2.inc
inherit deploy

S = "${WORKDIR}"

SRC_URI = "file://warning.bin \
    ${@bb.utils.contains("MACHINE_FEATURES", "gigabluelcd220", "file://lcdwaitkey220.bin file://lcdwarning220.bin" , "", d)} \
"

ALLOW_EMPTY_${PN} = "1"

do_deploy() {
    if [ -e lcdwaitkey220.bin ]; then
        install -m 0644 lcdwaitkey220.bin ${DEPLOYDIR}/lcdwaitkey220.bin
        install -m 0644 lcdwarning220.bin ${DEPLOYDIR}/lcdwarning220.bin
    fi
    if [ -e warning.bin ]; then
        install -m 0644 warning.bin ${DEPLOYDIR}/warning.bin
    fi
}

addtask deploy before do_build after do_install
