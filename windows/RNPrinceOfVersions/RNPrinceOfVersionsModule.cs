using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Prince.Of.Versions.RNPrinceOfVersions
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNPrinceOfVersionsModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNPrinceOfVersionsModule"/>.
        /// </summary>
        internal RNPrinceOfVersionsModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNPrinceOfVersions";
            }
        }
    }
}
